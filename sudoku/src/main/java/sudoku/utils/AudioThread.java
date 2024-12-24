package sudoku.utils;

public class AudioThread {

    private static Thread audioThread;
    private static boolean isPlaying = false;

    public static void threadInit(String filePath) {
        if (!isPlaying) {
            audioThread = new Thread(() -> {
                isPlaying = true;
                AudioPlayer.playSound(filePath);
                isPlaying = false;
            });

            audioThread.start();
        }
    }

    public static void stopMusic() {
        if (audioThread != null && audioThread.isAlive()) {
            audioThread.interrupt();
            isPlaying = false;
        }
    }

    public static boolean isMusicPlaying() {
        return isPlaying;
    }
}
