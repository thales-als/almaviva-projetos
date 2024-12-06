package sudoku;

import sudoku.ui.ConsoleUI;
import sudoku.model.SudokuGrid;

public class Main {
    public static void main(String[] args) {
        // Criação do tabuleiro de Sudoku
        SudokuGrid sudokuGrid = new SudokuGrid();

        // Criação da interface de usuário do console e início do jogo
        ConsoleUI consoleUI = new ConsoleUI(sudokuGrid);
        consoleUI.startGame();
    }
}
