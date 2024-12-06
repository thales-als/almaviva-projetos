package main.java.br.com.almaviva.crud_java.controller;

import main.java.br.com.almaviva.crud_java.model.Game;
import main.java.br.com.almaviva.crud_java.repository.GameRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GameController {
    private final GameRepository repository = new GameRepository();

    public void createGame(String title, String genre, String platform, LocalDate releaseDate, String developer) throws IOException {
        int newId = repository.findAll().size() + 1;
        Game game = new Game(newId, title, genre, platform, releaseDate, developer);
        repository.save(game);
    }

    public List<Game> getAllGames() throws IOException {
        return repository.findAll();
    }

    public Optional<Game> getGameById(int id) throws IOException {
        return repository.findById(id);
    }

    public void updateGame(Game updatedGame) throws IOException {
        repository.update(updatedGame);
    }

    public void deleteGame(int id) throws IOException {
        repository.delete(id);
    }
}
