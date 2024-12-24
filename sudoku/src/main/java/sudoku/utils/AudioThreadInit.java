package sudoku.utils;

public class AudioThreadInit {

    private static Thread audioThread;
    private static boolean isPlaying = false;

    public static void threadInit(String filePath) {
        if (!isPlaying) {
            audioThread = new Thread(() -> {
                isPlaying = true;
                AudioPlayer.playSound(filePath);  // Supondo que o AudioPlayer toque a música de forma contínua
                isPlaying = false;
            });

            audioThread.start();
        }
    }

    public static void stopMusic() {
        if (audioThread != null && audioThread.isAlive()) {
            audioThread.interrupt();  // Interrompe a thread, interrompendo o som
            isPlaying = false;
            System.out.println("Música parada.");
        }
    }

    public static boolean isMusicPlaying() {
        return isPlaying;
    }
}
