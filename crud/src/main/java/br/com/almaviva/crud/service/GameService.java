package main.java.br.com.almaviva.crud.service;

import java.util.List;
import java.util.UUID;

import main.java.br.com.almaviva.crud.model.Game;
import main.java.br.com.almaviva.crud.repository.GameRepository;

public class GameService {
    private final GameRepository gameRepository;

    public GameService() {
        this.gameRepository = new GameRepository();
    }

    public void createGame(Game game) {
        gameRepository.saveGame(game);
    }

    public void listAllGames() {
        List<Game> games = gameRepository.loadAllGames();
        if (games.isEmpty()) {
            System.out.println("Nenhum jogo encontrado.");
        } else {
            games.forEach(System.out::println);
        }
    }

    public void findGameById(UUID id) {
        Game game = gameRepository.findGameById(id);
        if (game != null) {
            System.out.println(game);
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }

    public void updateGameById(UUID id, Game updatedGame) {
        boolean updated = gameRepository.updateGameById(id, updatedGame);
        if (updated) {
            System.out.println("Jogo atualizado com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }

    public void removeAllGames() {
        gameRepository.deleteAllGames();
        System.out.println("Todos os jogos foram removidos.");
    }

    public void removeGameById(UUID id) {
        boolean removed = gameRepository.deleteGameById(id);
        if (removed) {
            System.out.println("Jogo removido com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }
}