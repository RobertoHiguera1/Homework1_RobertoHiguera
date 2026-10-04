//------------------------------------------------------
// Homework 1
// Written by: (Roberto Higuera 23802701)
// Date: Friday, October 2nd, 2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Matrix} (Task 1).
 *
 * <p>The tests below are the ones the handout asks for; they are supplied so that you can run
 * them while you implement {@code Matrix}. They fail until the class works. The methods marked
 * {@code @Disabled} are yours to write: delete the annotation and fill in the body.</p>
 */
class MatrixTest {

    @ParameterizedTest(name = "new Matrix({0}, {1}) is rejected")
    @CsvSource({"0,4", "4,0", "-1,4", "4,-1", "0,0"})
    @DisplayName("the size constructor reports illegal arguments")
    void sizeConstructorRejectsIllegalDimensions(int rows, int columns) {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(rows, columns));
    }

    @Test
    @DisplayName("the array constructor reports illegal arguments")
    void arrayConstructorRejectsIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(null));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new int[0][0]));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new int[3][0]));
    }

    @ParameterizedTest(name = "a {0}x{1} matrix reports {0} rows and {1} columns")
    @CsvSource({"1,1", "2,4", "3,4", "7,2"})
    @DisplayName("the row and column methods return the expected number of rows and columns")
    void reportsItsDimensions(int rows, int columns) {
        Matrix fromSize = new Matrix(rows, columns);
        assertEquals(rows, fromSize.getRows());
        assertEquals(columns, fromSize.getColumns());

        Matrix fromArray = new Matrix(new int[rows][columns]);
        assertEquals(rows, fromArray.getRows());
        assertEquals(columns, fromArray.getColumns());
    }

    /**
     * Tests that getData() returns the values that were passed to the constructor.
     */
    @Test
    @DisplayName("getData returns the values that were passed to the constructor")
    void getDataReturnsTheStoredValues() {
        // TODO: build a matrix from an int[][] you write out by hand, then check with
        // TODO: assertArrayEquals that getData() gives those values back.
        // Create sample data, use it to build a matrix, and verify that getData()
        // returns the same values that were originally stored.
        int[][] values = { {7,6,5}, {6,7,8}, {1,5,6}};
        Matrix fromData = new Matrix(values);

        assertArrayEquals(values, fromData.getData());

    }
    /**
     * Tests that setData() replaces the matrix's original data with new data.
     */
    @Test
    @DisplayName("setData replaces the contents of the matrix")
    void setDataReplacesContents() {
        // TODO: create a matrix, call setData with a different array, and check that
        // TODO: getRows, getColumns and getData all reflect the new array.
        // Create a matrix, replace its original data with new data, and verify
        // that its dimensions and stored values now match the new data.
        int[][] setData = {{6,5,4}, {1,2,3}, {8,0,2}};

        Matrix matrix = new Matrix(2,2);

        matrix.setData(setData);

        assertEquals(3, matrix.getRows());
        assertEquals(3, matrix.getColumns());
        assertArrayEquals(setData, matrix.getData());
    }
}
