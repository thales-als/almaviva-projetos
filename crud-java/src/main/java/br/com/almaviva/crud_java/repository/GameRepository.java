package main.java.br.com.almaviva.crud_java.repository;

import main.java.br.com.almaviva.crud_java.model.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepository {
    private final File file = new File("/opt/dev/projects/github-personal/almaviva-projetos/crud-java/src/main/resources/games.txt");

    public List<Game> findAll() throws IOException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Game> games = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                games.add(Game.fromString(line));
            }
            return games;
        }
    }

    public Optional<Game> findById(int id) throws IOException {
        return findAll().stream().filter(game -> game.getId() == id).findFirst();
    }

    public void save(Game game) throws IOException {
        List<Game> games = findAll();
        games.add(game);
        writeToFile(games);
    }

    public void update(Game updatedGame) throws IOException {
        List<Game> games = findAll();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == updatedGame.getId()) {
                games.set(i, updatedGame);
                break;
            }
        }
        writeToFile(games);
    }

    public void delete(int id) throws IOException {
        List<Game> games = findAll();
        games.removeIf(game -> game.getId() == id);
        writeToFile(games);
    }

    private void writeToFile(List<Game> games) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Game game : games) {
                bw.write(game.toString());
                bw.newLine();
            }
        }
    }
}
