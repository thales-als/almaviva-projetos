package main.java.br.com.almaviva.crud.model;

import java.util.UUID;

public class Game {
    private UUID id;
    private String title;
    private String genre;
    private String platform;
    private String releaseDate;
    private String developer;

    public Game(String title, String genre, String platform, String releaseDate, String developer) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.developer = developer;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
               "\nTítulo: " + title +
               "\nGênero: " + genre +
               "\nPlataforma: " + platform +
               "\nData de Lançamento: " + releaseDate +
               "\nDesenvolvedor: " + developer + "\n";
    }

    public String toFileString() {
        return id + "," + title + "," + genre + "," + platform + "," + releaseDate + "," + developer;
    }

    public static Game fromString(String gameString) {
        String[] parts = gameString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Formato inválido para o jogo: " + gameString);
        }
        Game game = new Game(parts[1], parts[2], parts[3], parts[4], parts[5]);
        game.id = UUID.fromString(parts[0]); // Preserve original ID
        return game;
    }
}