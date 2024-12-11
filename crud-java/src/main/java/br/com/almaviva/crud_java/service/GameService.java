package main.java.br.com.almaviva.crud_java.service;

import main.java.br.com.almaviva.crud_java.dto.GameDto;
import main.java.br.com.almaviva.crud_java.model.Game;
import main.java.br.com.almaviva.crud_java.repository.GameRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public void saveGame(GameDto dto) throws IOException {
        validateGameDto(dto);
        int newId = repository.findAll().size() + 1;
        Game game = fromDto(dto, newId);
        repository.save(game);
    }

    public List<Game> findAllGames() throws IOException {
        return repository.findAll();
    }

    public Optional<Game> findGameById(int id) throws IOException {
        return repository.findById(id);
    }

    public void updateGame(int id, GameDto dto) throws IOException {
        validateGameDto(dto);
        Game updatedGame = fromDto(dto, id);
        repository.update(updatedGame);
    }

    public void deleteGame(int id) throws IOException {
        repository.delete(id);
    }

    public LocalDate getReleaseDate(GameDto dto) {
        return dto.releaseDate();
    }

    private void validateGameDto(GameDto dto) {
        if (dto.title() == null || dto.title().isEmpty()) {
            throw new IllegalArgumentException("O título do jogo é obrigatório.");
        }
        if (dto.releaseDate() == null || dto.releaseDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de lançamento não pode ser no futuro.");
        }
    }

    private Game fromDto(GameDto dto, int id) {
        return new Game(
                id,
                dto.title(),
                dto.genre(),
                dto.platform(),
                dto.releaseDate(),
                dto.developer()
        );
    }
}
