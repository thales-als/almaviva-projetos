package sudoku;

import java.io.Console;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Limpar a tela e alterar o fundo para preto
        System.out.print("\033[H\033[2J"); // Limpar a tela
        System.out.flush();
        System.out.print("\033[40m"); // Fundo preto

        // Texto do logo
        String logo = " "
        + " ▗▄▄▖▗▖ ▗▖▗▄▄▖ ▗▄▄▄▖▗▄▄▖      ▗▄▄▖▗▖ ▗▖▗▄▄▄   ▗▄▖ ▗▖ ▗▖▗▖ ▗▖\n"
        + "▐▌   ▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌ ▐▌    ▐▌   ▐▌ ▐▌▐▌  █ ▐▌ ▐▌▐▌▗▞▘▐▌ ▐▌\n"
        + " ▝▀▚▖▐▌ ▐▌▐▛▀▘ ▐▛▀▀▘▐▛▀▚▖     ▝▀▚▖▐▌ ▐▌▐▌  █ ▐▌ ▐▌▐▛▚▖ ▐▌ ▐▌\n"
        + "▗▄▄▞▘▝▚▄▞▘▐▌   ▐▙▄▄▖▐▌ ▐▌    ▗▄▄▞▘▝▚▄▞▘▐▙▄▄▀ ▝▚▄▞▘▐▌ ▐▌▝▚▄▞▘\n";

        // Texto do menu
        String[] menuOptions = {"New Game", "Leaderboards", "Quit Game"};

        // Dimensões do terminal (definidas estaticamente para simplificar)
        int terminalWidth = 80;
        int terminalHeight = 24;

        // Centralizar o logo e o menu
        int totalContentHeight = logo.split("\\n").length + menuOptions.length + 2;
        int paddingTop = (terminalHeight - totalContentHeight) / 2;

        // Exibir espaço superior
        for (int i = 0; i < paddingTop; i++) {
            System.out.println();
        }

        // Exibir logo
        for (String line : logo.split("\\n")) {
            System.out.println(centerText(line, terminalWidth));
        }

        // Espaço entre logo e menu
        System.out.println();

        // Exibir menu
        for (String option : menuOptions) {
            System.out.println(centerText(option, terminalWidth));
        }

        // Resetar cor de fundo
        System.out.print("\033[0m");

        // Reproduzir som após 0,5 segundo
        try {
            Thread.sleep(250); // Delay de 0,5 segundo
            playSound("/opt/dev/projects/github-personal/almaviva-projetos/sudoku/src/main/resources/announcer.wav");
        } catch (Exception e) {
            System.err.println("Erro ao reproduzir som: " + e.getMessage());
        }
    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding < 0) padding = 0;
        return " ".repeat(padding) + text;
    }

    private static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            clip.drain();
            clip.stop();
        } catch (Exception e) {
            System.err.println("Erro ao carregar o som: " + e.getMessage());
        }
    }
}
