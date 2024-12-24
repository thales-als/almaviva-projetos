package sudoku.game;

public class Validator {

    public boolean isValidMove(int[][] grid, int row, int col, int num) {
        return isValidInRow(grid, row, num) && isValidInCol(grid, col, num) && isValidInSubgrid(grid, row, col, num);
    }

    private boolean isValidInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInCol(int[][] grid, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInSubgrid(int[][] grid, int row, int col, int num) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
