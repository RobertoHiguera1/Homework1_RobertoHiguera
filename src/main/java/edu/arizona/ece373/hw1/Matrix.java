//------------------------------------------------------
// Homework 1
// Written by: (Roberto Higuera 23802701)
// Date: Friday, October 2nd, 2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A rectangular, mutable matrix of {@code int} values.
 *
 * <p>Task 1 of the homework. The array of cells must stay {@code private}: everything outside
 * this class reaches the cells through the methods below.</p>
 *
 *
 * <p>This class creates and manages a rectangular matrix of integer values.
 * It allows the program to create matrices, access and change individual
 * cells, replace the matrix data, and determine the number of rows and columns.</p>
 */
public class Matrix {

    // TODO: declare the private field `data` holding a two-dimensional array of int.
    // Stores the values of the matrix while keeping the data private.
    private int[][] data;
    /**
     * Creates a matrix of the given size whose cells are all zero.
     *
     * @param rows    the number of rows, must be at least 1
     * @param columns the number of columns, must be at least 1
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is less than 1
     */
    public Matrix(int rows, int columns) {
        // TODO: check that rows >= 1 and columns >= 1 (IllegalArgumentException otherwise),
        // TODO: then allocate the array field with that many rows and columns.

        if ((rows < 1) || (columns < 1)) {
             throw new IllegalArgumentException();
        }
        data = new int[rows][columns];
    }

    /**
     * Creates a matrix that holds the given two-dimensional array of cells.
     *
     * @param data the cells, must be non-null, rectangular and at least 1x1
     * @throws IllegalArgumentException if {@code data} is {@code null}, has no rows, has no
     *                                  columns, has a {@code null} row, or is jagged
     */
    public Matrix(int[][] data) {
        // TODO: validate the argument (not null, at least 1 row, at least 1 column,
        // TODO: every row the same length) and store it in the field.
        // Make sure the provided array exists before trying to access it.
        if (data == null) {
            throw new IllegalArgumentException();
        }
        // Make sure the matrix contains at least one row.
        if (data.length < 1) {
            throw new IllegalArgumentException();
        }
        // Make sure the first row contains at least one column.
        if (data[0].length < 1) {
            throw new IllegalArgumentException();
        }
        // Make sure every row has the same number of columns.
        for (int i = 1; i < data.length; i++){
            if ( data[i] == null || data[i].length != data[0].length) {
                throw new IllegalArgumentException();
            }
        }
       this.data = data;
    }

    /**
     * Returns the cells of this matrix.
     *
     * @return the current values, {@code result[row][column]}
     */
    public int[][] getData() {
        // TODO: return the cells. Think about whether you hand out the field itself or a copy.
        // Create a copy so outside code cannot directly change the matrix's data.
        int[][] copy = new int[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                copy[i][j] = data[i][j];
            }
        }
        return copy;
    }

    /**
     * Replaces all cells of this matrix.
     *
     * <p>{@code GameOfLife.step()} uses this method to install the next generation.</p>
     *
     * @param data the new cells, must be non-null, rectangular and at least 1x1
     * @throws IllegalArgumentException if {@code data} is not a legal matrix body
     */
    public void setData(int[][] data) {
        // TODO: validate exactly like the array constructor, then store.
        // Make sure the provided array exists before trying to access it.
        if (data == null) {
            throw new IllegalArgumentException();
        }
        // Make sure the matrix contains at least one row.
        if (data.length < 1) {
            throw new IllegalArgumentException();
        }
        // Make sure the first row contains at least one column.
        if (data[0].length < 1) {
            throw new IllegalArgumentException();
        }
        // Make sure every row has the same number of columns.
        for (int i = 1; i < data.length; i++){
            if ( data[i] == null || data[i].length != data[0].length) {
                throw new IllegalArgumentException();
            }
        }
        this.data = data;
    }

    /**
     * Returns the number of rows of this matrix, i.e. the first dimension of the array.
     *
     * @return the row count, always at least 1
     */
    public int getRows() {
        // TODO: return the number of rows.
        return data.length;
    }

    /**
     * Returns the number of columns of this matrix, i.e. the second dimension of the array.
     *
     * @return the column count, always at least 1
     */
    public int getColumns() {
        // TODO: return the number of columns.
        return data[0].length;
    }

    /**
     * Returns the value of a single cell.
     *
     * @param row    the row index, {@code 0 <= row < getRows()}
     * @param column the column index, {@code 0 <= column < getColumns()}
     * @return the value stored in that cell
     * @throws IndexOutOfBoundsException if the coordinate is outside the matrix
     */
    public int get(int row, int column) {
        // TODO: bounds-check the coordinate and return the cell.
        // Prevent access to a cell outside the matrix.
        if ((row < 0) || (column < 0) || (row >= data.length) || (column >= data[0].length)) {
            throw new IndexOutOfBoundsException();
        }

        return data[row][column];
    }

    /**
     * Stores a value in a single cell.
     *
     * @param row    the row index, {@code 0 <= row < getRows()}
     * @param column the column index, {@code 0 <= column < getColumns()}
     * @param value  the value to store
     * @throws IndexOutOfBoundsException if the coordinate is outside the matrix
     */
    public void set(int row, int column, int value) {
        // TODO: bounds-check the coordinate and write the cell.
        // Prevent access to a cell outside the matrix.
        if ((row < 0) || (column < 0) || (row >= data.length) || (column >= data[0].length)) {
            throw new IndexOutOfBoundsException();
        }

        data[row][column] = value;

    }

}
