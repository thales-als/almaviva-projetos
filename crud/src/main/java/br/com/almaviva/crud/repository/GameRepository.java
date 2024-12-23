package main.java.br.com.almaviva.crud.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.java.br.com.almaviva.crud.model.Game;

public class GameRepository {
    private static final String FILE_PATH = "src/main/resources/games.txt";

    public void saveGame(Game game) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(game.toFileString() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public List<Game> loadAllGames() {
        List<Game> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(Game.fromString(line));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os jogos: " + e.getMessage());
        }
        return games;
    }

    public Game findGameById(UUID id) {
        List<Game> games = loadAllGames();
        return games.stream().filter(game -> game.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean updateGameById(UUID id, Game updatedGame) {
        List<Game> games = loadAllGames();
        boolean updated = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Game game : games) {
                if (game.getId().equals(id)) {
                    if (!updatedGame.getTitle().isEmpty()) game.setTitle(updatedGame.getTitle());
                    if (!updatedGame.getGenre().isEmpty()) game.setGenre(updatedGame.getGenre());
                    if (!updatedGame.getPlatform().isEmpty()) game.setPlatform(updatedGame.getPlatform());
                    if (!updatedGame.getReleaseDate().isEmpty()) game.setReleaseDate(updatedGame.getReleaseDate());
                    if (!updatedGame.getDeveloper().isEmpty()) game.setDeveloper(updatedGame.getDeveloper());
                    writer.write(game.toFileString() + "\n");
                    updated = true;
                } else {
                    writer.write(game.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o jogo: " + e.getMessage());
        }
        return updated;
    }

    public void deleteAllGames() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Erro ao deletar todos os jogos: " + e.getMessage());
        }
    }

    public boolean deleteGameById(UUID id) {
        List<Game> games = loadAllGames();
        boolean removed = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Game game : games) {
                if (!game.getId().equals(id)) {
                    writer.write(game.toFileString() + "\n");
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover o jogo: " + e.getMessage());
        }
        return removed;
    }
}