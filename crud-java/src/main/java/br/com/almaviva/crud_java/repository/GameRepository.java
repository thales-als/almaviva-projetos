package main.java.br.com.almaviva.crud_java.repository;

import main.java.br.com.almaviva.crud_java.model.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepository {
    private static final String FILE_PATH = "/opt/dev/projects/github-personal/almaviva-projetos/crud-java/src/main/resources/games.txt";

    public List<Game> findAll() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Game> games = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(Game.fromString(line));
            }
            return games;
        }
    }

    public Optional<Game> findById(int id) throws IOException {
        return findAll().stream().filter(game -> game.id() == id).findFirst();
    }

    public void save(Game game) throws IOException {
        List<Game> games = findAll();
        games.add(game);
        writeToFile(games);
    }

    public void update(Game updatedGame) throws IOException {
        List<Game> games = findAll();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).id() == updatedGame.id()) {
                games.set(i, updatedGame);
                break;
            }
        }
        writeToFile(games);
    }

    public void delete(int id) throws IOException {
        List<Game> games = findAll();
        games.removeIf(game -> game.id() == id);
        writeToFile(games);
    }

    private void writeToFile(List<Game> games) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Game game : games) {
                writer.write(game.toString());
                writer.newLine();
            }
        }
    }
}
