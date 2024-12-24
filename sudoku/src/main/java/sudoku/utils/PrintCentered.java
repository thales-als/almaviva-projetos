package sudoku.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PrintCentered {
	public static void printCenteredText(String text) {
	    int terminalWidth = 200;
	    String[] lines = text.split("\n");

	    for (String line : lines) {
	        int padding = (terminalWidth - line.length()) / 2;
	        String centeredLine = " ".repeat(padding) + line;
	        System.out.println(centeredLine);
	    }
	}
	
	public void printCenteredBoard(String boardText) {
        int width = 200;
        String[] lines = boardText.split("\n");

        for (String line : lines) {
            printCenteredText(line);
        }
    }
}
