package sudoku;

import java.util.Scanner;

import sudoku.game.Game;

import static sudoku.utils.AudioThreadInit.threadInit;
import static sudoku.utils.ClearConsole.clear;
import static sudoku.utils.PrintCentered.printCenteredText;
import static sudoku.utils.PathsAndData.*;
import static sudoku.leaderboards.LeaderboardsDisplay.printLBMenu;
import static sudoku.game.Game.*;

public class Main {

    private static final String[] MENU_OPTIONS = {
        "1. Novo Jogo", 
        "2. Placar de líderes", 
        "3. Sair do jogo"
    };

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        clear();
        printCenteredText(MAIN_MENU_LOGO.getString());
        printMenuOptions();
        threadInit(MENU_ANNOUNCER.getString());

        int option;
        
        do {
            option = getUserInput();
            handleUserSelection(option);
        } while (option != 3);
    }

    private static void printMenuOptions() {
        for (String option : MENU_OPTIONS) {
            printCenteredText("\n" + option);
        }
        
        printCenteredText("\nEscolha a opção desejada: ");
    }

    private static int getUserInput() {
        while (!sc.hasNextInt()) {
            sc.next();
            printCenteredText("Entrada inválida! Tente novamente.");
        }
        return sc.nextInt();
    }

    private static void handleUserSelection(int option) {
        switch (option) {
            case 1:
                startNewGame();
                break;
            case 2:
                printLBMenu();
                break;
            case 3:
                exitGame();
                break;
            default:
                printCenteredText("Opção inválida! Tente novamente.");
        }
    }

    private static void startNewGame() {
        Game gameExecutor = new Game();
        gameExecutor.executeGame();
    }

    private static void exitGame() {
        System.exit(0);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sc.close()));
    }
}
