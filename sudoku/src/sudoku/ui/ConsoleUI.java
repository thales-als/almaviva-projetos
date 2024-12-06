package sudoku.ui;

import sudoku.model.SudokuGrid;
import sudoku.service.SudokuSolver;

import java.util.Scanner;

public class ConsoleUI {
    private final SudokuGrid sudokuGrid;
    private final SudokuSolver solver = new SudokuSolver();
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    public void startGame() {
        while (true) {
            printGrid();
            if (sudokuGrid.isComplete()) {
                System.out.println("Parabéns, você completou o Sudoku!");
                break;
            }
            System.out.println("Digite sua jogada no formato (linha coluna valor), ou 'resolver' para completar:");
            String input = scanner.nextLine();

            if ("resolver".equalsIgnoreCase(input)) {
                if (solver.solve(sudokuGrid)) {
                    System.out.println("Sudoku resolvido:");
                } else {
                    System.out.println("Este Sudoku não tem solução.");
                }
                printGrid();
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length == 3) {
                try {
                    int row = Integer.parseInt(parts[0]) - 1;
                    int col = Integer.parseInt(parts[1]) - 1;
                    int value = Integer.parseInt(parts[2]);

                    if (!sudokuGrid.setValue(row, col, value)) {
                        System.out.println("Jogada inválida. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                }
            } else {
                System.out.println("Formato inválido. Tente novamente.");
            }
        }
    }

    private void printGrid() {
        int[][] grid = sudokuGrid.getGrid();

        System.out.println("    1 2 3   4 5 6   7 8 9"); // Cabeçalho das colunas
        System.out.println("  +-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            System.out.print((i + 1) + " | "); // Numeração das linhas
            for (int j = 0; j < 9; j++) {
                System.out.print((grid[i][j] == 0 ? "." : grid[i][j]) + " ");
                if ((j + 1) % 3 == 0) System.out.print("| "); // Separação por bloco 3x3
            }
            System.out.println();
            if ((i + 1) % 3 == 0) System.out.println("  +-------+-------+-------+"); // Linha horizontal de separação
        }
    }
}
