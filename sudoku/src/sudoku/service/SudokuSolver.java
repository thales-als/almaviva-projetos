package sudoku.service;

import sudoku.model.SudokuGrid;

public class SudokuSolver {
    public boolean solve(SudokuGrid sudokuGrid) {
        int[][] grid = sudokuGrid.getGrid();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (sudokuGrid.isValid(row, col, num)) {
                            grid[row][col] = num;
                            if (solve(sudokuGrid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
