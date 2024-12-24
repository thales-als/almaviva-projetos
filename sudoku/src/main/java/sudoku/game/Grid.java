package sudoku.game;

import sudoku.utils.PrintCentered;
import java.util.Random;
import static sudoku.utils.ClearConsole.clear;

public class Grid {

    private int[][] grid;
    private PrintCentered printCentered;

    public Grid() {
        grid = new int[9][9];
        printCentered = new PrintCentered();
        clear();
        generateRandomBoard();
    }

    public int[][] getGrid() {
        return grid;
    }

    public void generateRandomBoard() {
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(3) == 0) {
                    int num = rand.nextInt(9) + 1;
                    if (isValidMove(grid, i, j, num)) {
                        grid[i][j] = num;
                    }
                }
            }
        }
    }

    public void updateBoard(int row, int col, int num) {
        grid[row][col] = num;
    }

    public void printBoard() {
    	clear();
    	
        StringBuilder boardText = new StringBuilder();

        boardText.append("    1 2 3   4 5 6   7 8 9\n");
        boardText.append("  +-------+-------+-------+\n");

        for (int i = 0; i < 9; i++) {
            boardText.append((char) ('A' + i) + " | ");

            for (int j = 0; j < 9; j++) {
                boardText.append((grid[i][j] == 0 ? ". " : grid[i][j] + " "));
                if (j == 2 || j == 5) {
                    boardText.append("  ");
                }
            }

            boardText.append("\n");

            if (i == 2 || i == 5) {
                boardText.append("  +-------+-------+-------+\n");
            }
        }

        boardText.append("  +-------+-------+-------+\n");

        printCentered.printCenteredBoard(boardText.toString());
    }

    public boolean isValidMove(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
