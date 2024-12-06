package main.java.br.com.almaviva.crud_java.model;

import java.time.LocalDate;

public class Game {
    private int id;
    private String title;
    private String genre;
    private String platform;
    private LocalDate releaseDate;
    private String developer;

    public Game(int id, String title, String genre, String platform, LocalDate releaseDate, String developer) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.developer = developer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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
        return id + ";" + title + ";" + genre + ";" + platform + ";" + releaseDate + ";" + developer;
    }

    public static Game fromString(String data) {
        String[] parts = data.split(";");
        return new Game(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                LocalDate.parse(parts[4]),
                parts[5]
        );
    }
}
