package sudoku;

import sudoku.model.SudokuGrid;
import sudoku.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        SudokuGrid grid = new SudokuGrid();
        grid.initializeSampleGrid(); // Preenche com um exemplo para jogar/testar

        ConsoleUI consoleUI = new ConsoleUI(grid);
        consoleUI.startGame();
    }
}
