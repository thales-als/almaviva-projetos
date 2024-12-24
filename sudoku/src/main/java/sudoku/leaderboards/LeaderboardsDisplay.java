package sudoku.leaderboards;

import static sudoku.leaderboards.GameRecord.readAll;
import static sudoku.utils.ClearConsole.clear;
import static sudoku.utils.PathsAndData.LEADERBOARDS_LOGO;
import static sudoku.utils.PrintCentered.printCenteredText;

import java.util.List;
import java.util.Scanner;

import sudoku.Main;

public class LeaderboardsDisplay {
	
	private static final String[] MENU_OPTIONS = {
	        "1. Voltar para o Menu Principal",
	        "2. Sair do jogo"
	    };

    private static final Scanner sc = new Scanner(System.in);

    public static void printLBMenu() {
        clear();
        printCenteredText(LEADERBOARDS_LOGO.getString());
        
        List<Player> players = readAll();
        printLeaderboard(players);
        
        printMenuOptions();
        
        int option;
        do {
            option = getUserInput();
            handleUserInput(option);
        } while (option != 2);
    }

    private static void printLeaderboard(List<Player> players) {
        for (Player player : players) {
            printCenteredText(formatPlayerRecord(player));
        }
    }

    private static String formatPlayerRecord(Player player) {
        return String.format("%s - Tempo: %.2f segundos", player.getName(), player.getCompletionTime());
    }

    private static void printMenuOptions() {
    	for (String option : MENU_OPTIONS) {
    		printCenteredText("\n" + option);
    	}
    	
    	printCenteredText("\nEscolha a opção desejada: ");
    }

    private static void handleUserInput(int option) {
        switch (option) {
            case 1:
                Main.main(null);
                break;
            case 2:
                exitGame();
                break;
            default:
                printCenteredText("Opção inválida! Tente novamente.");
        }
    }

    private static int getUserInput() {
        while (!sc.hasNextInt()) {
            sc.next();
            printCenteredText("Entrada inválida! Tente novamente.");
        }
        return sc.nextInt();
    }

    private static void exitGame() {
        System.exit(0);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sc.close()));
    }
}
