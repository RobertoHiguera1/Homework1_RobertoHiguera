//------------------------------------------------------
// Homework 1
// Written by: (Roberto Higuera 23802701)
// Date: Friday, October 4th, 2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link MatrixOutlinePrinter} and {@link BoolMatrixPrinter} (Task 2).
 *
 * <p>The first three tests are the ones named in the handout and are used for grading; they are
 * supplied so that you can run them while you implement the printers. The
 * {@code @Disabled} methods are yours to write.</p>
 */

/*
 * Program Description: Tests the matrix printer classes to make sure they produce the expected output.
 */
class MatrixPrinterTest {

    @Test
    @DisplayName("the outline printer prints an outline of the right size for a 3x4 matrix")
    void outlinePrinterFramesThreeByFour() {
        MatrixPrinter printer = new MatrixOutlinePrinter();
        String expected = ""
                + "+----+\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "+----+\n";
        assertEquals(expected, printer.print(new Matrix(3, 4)));
    }

    @Test
    @DisplayName("the boolean printer rejects a matrix that holds a value other than 0 or 1")
    void boolPrinterRejectsIllegalValues() {
        Matrix illegal = new Matrix(new int[][] {{0, 1}, {1, 2}});
        assertThrows(IllegalArgumentException.class, () -> new BoolMatrixPrinter().print(illegal));
    }

    @Test
    @DisplayName("the boolean printer renders the example from the handout")
    void boolPrinterRendersHandoutExample() {
        Matrix matrix = new Matrix(new int[][] {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
        });
        String expected = ""
                + "+----+\n"
                + "|0010|\n"
                + "|0010|\n"
                + "+----+\n";
        assertEquals(expected, new BoolMatrixPrinter().print(matrix));
    }

    @Test
    @DisplayName("the boolean printer renders a second matrix of your choice")
    void boolPrinterRendersASecondMatrix() {
        // TODO: pick a second 0/1 matrix with a different shape, write down the string you
        // TODO: expect, and compare it with assertEquals.

        // Use a different shape to verify the printer works beyond the handout example.
        Matrix matrix = new Matrix(new int[][] {
                {0, 1, 1, },
                {0, 0, 1, },
                {1, 0, 1, }
        });
        // Compare the expected outline with the printer's actual output.
        String expected = ""
                + "+---+\n"
                + "|011|\n"
                + "|001|\n"
                + "|101|\n"
                + "+---+\n";
        assertEquals(expected, new BoolMatrixPrinter().print(matrix));

    }

    @Test
    @DisplayName("a BoolMatrixPrinter used through the MatrixOutlinePrinter type still prints values")
    void overridingBeatsTheStaticType() {
        // TODO: store a new BoolMatrixPrinter in a MatrixOutlinePrinter variable, print a
        // TODO: matrix through it, and check that you see the values and not blanks.

        // Use the parent type to verify that the subclass still overrides printRow().
        MatrixOutlinePrinter variable = new BoolMatrixPrinter();
        Matrix matrix = new Matrix(new int[][]{
                {0, 0, 1,},
                {0, 0, 1,},
                {1, 0, 1,}
        });
        // Verify that the Boolean values are printed instead of blank spaces.
        String expected = ""
                + "+---+\n"
                + "|001|\n"
                + "|001|\n"
                + "|101|\n"
                + "+---+\n";
        assertEquals(expected, variable.print(matrix));
    }
}
