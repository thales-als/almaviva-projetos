package main.java.br.com.almaviva.crud_java.model;

import java.time.LocalDate;

public record Game(
        int id,
        String title,
        String genre,
        String platform,
        LocalDate releaseDate,
        String developer
) {
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
