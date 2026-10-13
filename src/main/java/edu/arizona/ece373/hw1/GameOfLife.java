//------------------------------------------------------
// Homework 1
// Written by: (Roberto Higuera 23802701)
// Date: Tuesday, October 13th, 2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A Conway's Game of Life field.
 *
 * <p>Task 4 of the homework. The field <em>is a</em> {@link Matrix} in which {@code 1} means the
 * cell is alive and {@code 0} means it is dead. Cells outside the field always count as dead, so
 * the field does not wrap around at the edges.</p>
 *
 * <p>The rules for one generation are:</p>
 * <ul>
 *   <li>a live cell with fewer than two live neighbours dies;</li>
 *   <li>a live cell with two or three live neighbours survives;</li>
 *   <li>a live cell with more than three live neighbours dies;</li>
 *   <li>a dead cell with exactly three live neighbours becomes alive.</li>
 * </ul>
 */

/*
 * Program Description: Simulates Conway's Game of Life using live (1) and dead (0) cells.
 * Supports adding shapes, counting live neighbours, and advancing
 * generations. Demonstrates the Blinker and Glider patterns.
 */
public class GameOfLife extends Matrix {

    /** Value of a cell that is alive. */
    public static final int ALIVE = 1;

    /** Value of a cell that is dead. */
    public static final int DEAD = 0;

    /**
     * Creates an empty game field of the given size; every cell starts dead.
     *
     * @param rows    the height of the field, must be at least 1
     * @param columns the width of the field, must be at least 1
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is less than 1
     */
    public GameOfLife(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Stamps a shape onto the field with its top-left cell at the given coordinate.
     *
     * @param shape  the shape to place, must not be {@code null}
     * @param row    the field row that receives row 0 of the shape
     * @param column the field column that receives column 0 of the shape
     * @throws IllegalArgumentException if {@code shape} is {@code null}, if the coordinate is
     *                                  negative, or if the shape does not fit entirely on the field
     */
    public void addShape(Shape shape, int row, int column) {
        // TODO: reject null, negative coordinates, and shapes that would stick out of the field;
        // TODO: then copy every cell of the shape onto the field.

        // Reject invalid placements before accessing the shape's cells.
        if(shape == null) {
            throw new IllegalArgumentException();
        }
        if(row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }

        // Check the ending coordinates to ensure the entire shape fits.
        if(row + shape.getRows() > getRows() || column + shape.getColumns() > getColumns()) {
            throw new IllegalArgumentException();
        }

        // Copy each cell so the shape keeps its original arrangement.
        for(int i = 0; i < shape.getRows(); i++) {
            for(int j = 0; j < shape.getColumns(); j++) {
                int cellValue = shape.get(i,j);
                set(row + i, column + j, cellValue);
            }
        }
    }

    /**
     * Advances the field by one generation.
     *
     * <p>Compute the whole next generation into a local two-dimensional array first, and only
     * then install it with the inherited {@link Matrix#setData(int[][])}.</p>
     */
    public void step() {
        // TODO: allocate a local int[getRows()][getColumns()], fill it by applying the rules to
        // TODO: the current generation, then call setData(next).
        int[][] next = new int[getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                int liveNeighbours = countLiveNeighbours(i, j);
                int currentCell = get(i, j);

                // Apply the survival and death rules to currently live cells.
                if (currentCell == ALIVE) {
                    if (liveNeighbours == 2 || liveNeighbours == 3) {
                        next[i][j] = ALIVE;
                    } else {
                        next[i][j] = DEAD;
                    }
                    // A dead cell becomes alive only when it has three neighbours.
                } else {
                    if (liveNeighbours == 3) {
                        next[i][j] = ALIVE;
                    } else {
                        next[i][j] = DEAD;
                    }
                }
            }
        }

        setData(next);
    }

    /**
     * Counts how many of the eight cells around a coordinate are alive.
     *
     * @param row    the row of the cell whose neighbours are counted
     * @param column the column of the cell whose neighbours are counted
     * @return a number between 0 and 8
     */
    public int countLiveNeighbours(int row, int column) {
        // TODO: look at the eight surrounding coordinates and count the live ones,
        // TODO: skipping coordinates that fall outside the field.
        int liveNeighbours = 0;

        // Examine the surrounding 3-by-3 area, including the centre initially.
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {

                // Skip the cell whose neighbours are being counted.
                if (i == row && j == column) {
                    continue;
                }

                // Ignore coordinates outside the field.
                if (i >= 0 && i < getRows()
                        && j >= 0 && j < getColumns()) {
                    if (get(i, j) == ALIVE) {
                        liveNeighbours++;
                    }
                }
            }
        }

        return liveNeighbours;
    }

    /**
     * Showcase for Task 4: builds a field holding a blinker and a glider and steps it a fixed
     * number of generations.
     *
     * @param args optional; args[0] is the number of generations to run, default 20
     * @throws InterruptedException if the animation is interrupted while sleeping between frames
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: create a game field, add at least a Blinker and a Glider, then run a fixed
        // TODO: number of generations - read it from args[0] if present, else use 20:
        // TODO:   print the field with a BoolMatrixPrinter, call step(), and Thread.sleep a little.
        int generations = 20;

        if (args.length > 0) {
            generations = Integer.parseInt(args[0]);

            if (generations < 0) {
                throw new IllegalArgumentException();
            }
        }

        GameOfLife game = new GameOfLife(15, 30);

        System.out.println("==================================");
        System.out.println(" Welcome to Conway's Game of Life!");
        System.out.println("==================================");
        System.out.println("Generations to simulate: " + generations);
        System.out.println();

        // Add a blinker and a glider.
        game.addShape(Shape.blinker(), 2, 2);
        game.addShape(Shape.glider(), 6, 10);

        BoolMatrixPrinter printer = new BoolMatrixPrinter();

        // Include generation zero so the user can see the starting arrangement.
        // Pause only when another generation remains to avoid a final delay.
        for (int generation = 0; generation <= generations; generation++) {
            System.out.println("Generation " + generation);
            printer.print(game);

            if (generation < generations) {
                game.step();
                Thread.sleep(300);
            }
        }

        System.out.println("Simulation complete!");
    }
}
