package sudoku.model;

public class SudokuGrid {
    private final int[][] grid = new int[9][9];

    public int[][] getGrid() {
        return grid;
    }

    public void initializeSampleGrid() {
        // Exemplo de tabuleiro inicial
        int[][] sampleGrid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        for (int i = 0; i < 9; i++) {
            System.arraycopy(sampleGrid[i], 0, grid[i], 0, 9);
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
