package sudoku.utils;

public enum PathsAndData {
    
    MAIN_MENU_LOGO("""
         ▗▄▄▖▗▖ ▗▖▗▄▄▖ ▗▄▄▄▖▗▄▄▖      ▗▄▄▖▗▖ ▗▖▗▄▄▄   ▗▄▖ ▗▖ ▗▖▗▖ ▗▖
        ▐▌   ▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌ ▐▌    ▐▌   ▐▌ ▐▌▐▌  █ ▐▌ ▐▌▐▌▗▞▘▐▌ ▐▌
         ▝▀▚▖▐▌ ▐▌▐▛▀▘ ▐▛▀▀▘▐▛▀▚▖     ▝▀▚▖▐▌ ▐▌▐▌  █ ▐▌ ▐▌▐▛▚▖ ▐▌ ▐▌
        ▗▄▄▞▘▝▚▄▞▘▐▌   ▐▙▄▄▖▐▌ ▐▌    ▗▄▄▞▘▝▚▄▞▘▐▙▄▄▀ ▝▚▄▞▘▐▌ ▐▌▝▚▄▞▘
    """),
    
    LEADERBOARDS_LOGO("""
    ▗▖   ▗▄▄▄▖ ▗▄▖ ▗▄▄▄  ▗▄▄▄▖▗▄▄▖ ▗▄▄▖  ▗▄▖  ▗▄▖ ▗▄▄▖ ▗▄▄▄   ▗▄▄▖
    ▐▌   ▐▌   ▐▌ ▐▌▐▌  █ ▐▌   ▐▌ ▐▌▐▌ ▐▌▐▌ ▐▌▐▌ ▐▌▐▌ ▐▌▐▌  █ ▐▌   
    ▐▌   ▐▛▀▀▘▐▛▀▜▌▐▌  █ ▐▛▀▀▘▐▛▀▚▖▐▛▀▚▖▐▌ ▐▌▐▛▀▜▌▐▛▀▚▖▐▌  █  ▝▀▚▖
    ▐▙▄▄▖▐▙▄▄▖▐▌ ▐▌▐▙▄▄▀ ▐▙▄▄▖▐▌ ▐▌▐▙▄▞▘▝▚▄▞▘▐▌ ▐▌▐▌ ▐▌▐▙▄▄▀ ▗▄▄▞▘
    """),
    
    MENU_ANNOUNCER("/opt/dev/projects/github-personal/almaviva-projetos/sudoku/src/main/resources/sounds/announcer.wav"),
    DEFEAT_SE("/opt/dev/projects/github-personal/almaviva-projetos/sudoku/src/main/resources/sounds/defeat.wav"),
    VICTORY_SE("/opt/dev/projects/github-personal/almaviva-projetos/sudoku/src/main/resources/sounds/victory.wav"),
    LEADERBOARDS_DATA("/opt/dev/projects/github-personal/almaviva-projetos/sudoku/src/main/resources/leaderboards.txt");

    private final String str;

    PathsAndData(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }
}