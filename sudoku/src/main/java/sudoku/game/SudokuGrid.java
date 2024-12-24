package sudoku.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGrid {
    private final int[][] grid = new int[9][9];
    private static final Random random = new Random();

    public int[][] getGrid() {
        return grid;
    }

    public void generateRandomBoard() {
        fillGrid();
        removeValues();
    }

    private void fillGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                List<Integer> possibleValues = getPossibleValues(row, col);
                Collections.shuffle(possibleValues);

                for (int i = 0; i < Math.min(3, possibleValues.size()); i++) {
                    grid[row][col] = possibleValues.get(i);
                    if (isComplete()) {
                        return;
                    }
                }
            }
        }
    }

    private List<Integer> getPossibleValues(int row, int col) {
        List<Integer> possibleValues = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                possibleValues.add(num);
            }
        }
        return possibleValues;
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void removeValues() {
        int count = random.nextInt(41) + 30;
        while (count > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (grid[row][col] != 0) {
                grid[row][col] = 0;
                count--;
            }
        }
    }

    public boolean setValue(int row, int col, int value) {
        if (isValid(row, col, value)) {
            grid[row][col] = value;
            return true;
        }
        return false;
    }

    public boolean isValid(int row, int col, int value) {
        if (grid[row][col] != 0 || value < 1 || value > 9) return false;

        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value || grid[i][col] == value) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == value) return false;
            }
        }

        return true;
    }

    public boolean isComplete() {
        for (int[] row : grid) {
            for (int value : row) {
                if (value == 0) return false;
            }
        }
        return true;
    }
}
