package main.java.br.com.almaviva.crud_java.controller;

import main.java.br.com.almaviva.crud_java.dto.GameDto;
import main.java.br.com.almaviva.crud_java.model.Game;
import main.java.br.com.almaviva.crud_java.service.GameService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    public void createGame(GameDto dto) throws IOException {
        service.saveGame(dto);
    }

    public List<Game> getAllGames() throws IOException {
        return service.findAllGames();
    }

    public Optional<Game> getGameById(int id) throws IOException {
        return service.findGameById(id);
    }

    public void updateGame(int id, GameDto dto) throws IOException {
        service.updateGame(id, dto);
    }

    public void deleteGame(int id) throws IOException {
        service.deleteGame(id);
    }
}
