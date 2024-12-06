package sudoku.model;

import java.util.Random;

public class SudokuGrid {
    private final int[][] grid = new int[9][9];
    private static final Random random = new Random();

    public int[][] getGrid() {
        return grid;
    }

    // Método para gerar um Sudoku válido
    public void generateRandomBoard() {
        fillGrid();
        removeValues();
    }

    // Preenche o tabuleiro com um Sudoku completo
    private void fillGrid() {
        int[][] solution = new int[9][9];
        solveSudoku(solution);
        for (int i = 0; i < 9; i++) {
            System.arraycopy(solution[i], 0, grid[i], 0, 9);
        }
    }

    // Solucionador de Sudoku (Backtracking)
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
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Verifica se o número é válido
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

    // Remove alguns números aleatoriamente para criar um Sudoku jogável
    private void removeValues() {
        int count = random.nextInt(41) + 30; // Remover entre 30 a 70 células
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

        // Verifica linha e coluna
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value || grid[i][col] == value) return false;
        }

        // Verifica subgrade 3x3
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
