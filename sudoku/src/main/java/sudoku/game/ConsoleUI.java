package sudoku.game;

import static sudoku.utils.PathsAndData.DEFEAT_SE;
import static sudoku.utils.PathsAndData.VICTORY_SE;

import java.util.Scanner;

import sudoku.Main;
import sudoku.leaderboards.GameRecord;
import sudoku.leaderboards.Player;
import sudoku.utils.AudioThread;
import sudoku.utils.ClearConsole;
import sudoku.utils.PrintCentered;

public class ConsoleUI {
    private final SudokuGrid sudokuGrid;
    private final SudokuSolver solver = new SudokuSolver();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public ConsoleUI(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    public void startGame() {
        sudokuGrid.generateRandomBoard();
        long startTime = System.currentTimeMillis();

        while (isRunning) {
            ClearConsole.clear();
            printGrid();

            if (sudokuGrid.isComplete()) {
                handleGameCompletion(startTime);
                break;
            }

            promptForPlayerMove();
        }
    }

    private void promptForPlayerMove() {
    	PrintCentered.printCenteredText("Digite 'sair' para sair da partida.");
        PrintCentered.printCenteredText("Digite sua jogada no formato (A1 5), ou 'resolver' para completar:");
        String input = scanner.nextLine();

        if ("sair".equalsIgnoreCase(input)) {
            Main.main(null);
        } else if ("resolver".equalsIgnoreCase(input)) {
            attemptToSolveSudoku();
        } else {
            processPlayerMove(input);
        }
    }

    private void handleGameCompletion(long startTime) {
        stopMusicAndPlayVictory();
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime) / 1000;

        PrintCentered.printCenteredText("Parabéns, você completou o Sudoku em " + timeTaken + " segundos!");
        String playerName = promptForPlayerName();
        saveGameRecord(playerName, (int) timeTaken);
        askToPlayAgain();
    }

    private String promptForPlayerName() {
        PrintCentered.printCenteredText("Digite seu nome: ");
        return scanner.nextLine();
    }

    private void saveGameRecord(String playerName, int timeTaken) {
        Player player = new Player(playerName, timeTaken);
        GameRecord.create(player);
    }

    private void askToPlayAgain() {
        PrintCentered.printCenteredText("Deseja jogar novamente? (s/n)");
        String response = scanner.nextLine();

        if ("s".equalsIgnoreCase(response)) {
            sudokuGrid.generateRandomBoard();
            startGame();
        } else {
            Main.main(null);
        }
    }

    private void attemptToSolveSudoku() {
        boolean solvable = solver.solve(sudokuGrid);

        if (!solvable) {
            handleDefeat();
        } else {
            ClearConsole.clear();
            printGrid();
        }
    }

    private void handleDefeat() {
        stopMusicAndPlayDefeat();
        PrintCentered.printCenteredText("Este Sudoku não tem solução.");
        askToPlayAgain();
    }

    private void processPlayerMove(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 2) {
            PrintCentered.printCenteredText("Formato inválido. Tente novamente.");
            return;
        }

        try {
            String cell = parts[0].toUpperCase();
            int value = Integer.parseInt(parts[1]);

            int row = cell.charAt(0) - 'A';
            int col = Integer.parseInt(cell.substring(1)) - 1;

            if (!sudokuGrid.setValue(row, col, value)) {
                PrintCentered.printCenteredText("Jogada inválida. Tente novamente.");
            }
        } catch (Exception e) {
            PrintCentered.printCenteredText("Entrada inválida. Tente novamente.");
        }
    }

    private void stopMusicAndPlayVictory() {
        playMusicWithDelay(VICTORY_SE.getString());
    }

    private void stopMusicAndPlayDefeat() {
        playMusicWithDelay(DEFEAT_SE.getString());
    }

    private void playMusicWithDelay(String musicPath) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Não foi possível tocar a música: " + e.getMessage());
        }
        AudioThread.threadInit(musicPath);
    }

    private void printGrid() {
        int[][] grid = sudokuGrid.getGrid();

        PrintCentered.printCenteredText("    1 2 3   4 5 6   7 8 9");
        PrintCentered.printCenteredText("  +-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            printGridRow(i, grid);
        }
    }

    private void printGridRow(int i, int[][] grid) {
        StringBuilder line = new StringBuilder((char) ('A' + i) + " | ");
        for (int j = 0; j < 9; j++) {
            line.append((grid[i][j] == 0 ? "." : grid[i][j]) + " ");
            if ((j + 1) % 3 == 0) line.append("| ");
        }
        PrintCentered.printCenteredText(line.toString());

        if ((i + 1) % 3 == 0) {
            PrintCentered.printCenteredText("  +-------+-------+-------+");
        }
    }
}
