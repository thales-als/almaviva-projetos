package main.java.br.com.almaviva.crud.controller;

import java.util.UUID;

import main.java.br.com.almaviva.crud.model.Game;
import main.java.br.com.almaviva.crud.service.GameService;

public class GameController {
    private final GameService gameService;

    public GameController() {
        this.gameService = new GameService();
    }

    public void createGame(Game game) {
        gameService.createGame(game);
    }

    public void listAllGames() {
        gameService.listAllGames();
    }

    public void findGameById(UUID id) {
        gameService.findGameById(id);
    }

    public void updateGameById(UUID id, Game updatedGame) {
        gameService.updateGameById(id, updatedGame);
    }

    public void removeAllGames() {
        gameService.removeAllGames();
    }

    public void removeGameById(UUID id) {
        gameService.removeGameById(id);
    }
}