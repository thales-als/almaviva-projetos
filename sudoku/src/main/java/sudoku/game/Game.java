package sudoku.game;

import java.util.Scanner;

import static sudoku.utils.PrintCentered.printCenteredText;
import sudoku.utils.AudioThreadInit;
import static sudoku.utils.PathsAndData.*;

public class Game {

    private Grid sudokuGrid;
    private Validator sudokuService;
    private Solver sudokuSolver;
    private Scanner scanner;

    public Game() {
        this.sudokuGrid = new Grid();
        this.sudokuService = new Validator();
        this.sudokuSolver = new Solver();
        this.scanner = new Scanner(System.in);
    }

    public void executeGame() {
        boolean gameInProgress = true;
        boolean playAgain = false;

        while (true) {
            sudokuGrid.printBoard();
            // Inicia a música ao rodar o jogo
            AudioThreadInit.threadInit(GAME_THEME.getString());  // Altere para o caminho correto do arquivo de áudio

            while (gameInProgress) {
                printCenteredText("\n\nDigite a jogada (ex: A1 5 ou 'resolver' para tentar resolver) ou 'stopSong' para parar a música: ");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("stopSong")) {
                    AudioThreadInit.stopMusic();
                	printCenteredText("Música parada.");
                } else if (input.equals("resolver")) {
                    if (sudokuSolver.solve(sudokuGrid.getGrid())) {
                        sudokuGrid.printBoard();
                        printCenteredText("Tabuleiro resolvido!");
                    } else {
                        printCenteredText("Não foi possível resolver o Sudoku.");
                    }
                    break;
                } else if (input.matches("[a-i][1-9] \\d")) {
                    char rowChar = input.charAt(0);
                    int col = Character.getNumericValue(input.charAt(1)) - 1;
                    int row = rowChar - 'a';
                    int num = Character.getNumericValue(input.charAt(3));

                    if (sudokuService.isValidMove(sudokuGrid.getGrid(), row, col, num)) {
                        sudokuGrid.updateBoard(row, col, num);
                        sudokuGrid.printBoard();
                    } else {
                        printCenteredText("Jogada inválida, tente novamente.");
                    }

                    if (isSolved(sudokuGrid.getGrid())) {
                        printCenteredText("Parabéns! Você resolveu o Sudoku.");
                        gameInProgress = false;
                    }
                } else {
                    System.out.println("Entrada inválida! Tente novamente.");
                }
            }

            // Pergunta se o usuário quer jogar novamente ou voltar ao menu
            printCenteredText("\nDeseja jogar novamente? (sim/não)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("sim")) {
                sudokuGrid = new Grid(); // Reinicia o tabuleiro
                gameInProgress = true; // Reinicia o jogo
            } else {
                printCenteredText("Voltando ao menu inicial...");
                break; // Encerra o jogo e volta ao menu
            }
        }
    }

    private static boolean isSolved(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
