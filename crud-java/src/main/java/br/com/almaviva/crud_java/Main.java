package main.java.br.com.almaviva.crud_java;

import main.java.br.com.almaviva.crud_java.controller.GameController;
import main.java.br.com.almaviva.crud_java.dto.GameDto;
import main.java.br.com.almaviva.crud_java.model.Game;
import main.java.br.com.almaviva.crud_java.repository.GameRepository;
import main.java.br.com.almaviva.crud_java.service.GameService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GameRepository repository = new GameRepository();
    private static final GameService service = new GameService(repository);
    private static final GameController controller = new GameController(service);

    public static void main(String[] args) {
        int option;
        do {
            printMenu();
            option = getUserOption();
            processOption(option);
        } while (option != 6);
    }

    private static void printMenu() {
        System.out.println("\n-----------------------------------");
        System.out.println("           CRUD de Jogos           ");
        System.out.println("-----------------------------------");
        System.out.println("1. Criar um novo jogo");
        System.out.println("2. Listar todos os jogos");
        System.out.println("3. Buscar jogo por ID");
        System.out.println("4. Atualizar um jogo");
        System.out.println("5. Excluir um jogo");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int getUserOption() {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine());
                if (option >= 1 && option <= 6) {
                    return option;
                } else {
                    System.out.print("Opção inválida. Tente novamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número de 1 a 6: ");
            }
        }
    }

    private static void processOption(int option) {
        switch (option) {
            case 1:
                createGame();
                break;
            case 2:
                listGames();
                break;
            case 3:
                findGameById();
                break;
            case 4:
                updateGame();
                break;
            case 5:
                deleteGame();
                break;
            case 6:
                System.out.println("Saindo... Até logo!");
                break;
        }
    }

    private static void createGame() {
        try {
            System.out.print("\nDigite o título do jogo: ");
            String title = scanner.nextLine();
            System.out.print("Digite o gênero do jogo: ");
            String genre = scanner.nextLine();
            System.out.print("Digite a plataforma do jogo: ");
            String platform = scanner.nextLine();
            System.out.print("Digite a data de lançamento (formato YYYY-MM-DD): ");
            LocalDate releaseDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Digite o desenvolvedor do jogo: ");
            String developer = scanner.nextLine();

            GameDto gameDto = new GameDto(title, genre, platform, releaseDate, developer);
            controller.createGame(gameDto);

            System.out.println("Jogo criado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar o jogo: " + e.getMessage());
        }
    }

    private static void listGames() {
        try {
            List<Game> games = controller.getAllGames();
            if (games.isEmpty()) {
                System.out.println("Nenhum jogo cadastrado.");
            } else {
                System.out.println("\nLista de Jogos:");
                games.forEach(game -> System.out.println(game));
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar jogos: " + e.getMessage());
        }
    }

    private static void findGameById() {
        try {
            System.out.print("\nDigite o ID do jogo a ser buscado: ");
            int id = Integer.parseInt(scanner.nextLine());

            Optional<Game> gameOpt = controller.getGameById(id);
            if (gameOpt.isPresent()) {
                System.out.println("Jogo encontrado: " + gameOpt.get());
            } else {
                System.out.println("Jogo não encontrado!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar o jogo: " + e.getMessage());
        }
    }

    private static void updateGame() {
        try {
            System.out.print("\nDigite o ID do jogo a ser atualizado: ");
            int id = Integer.parseInt(scanner.nextLine());

            Optional<Game> gameOpt = controller.getGameById(id);
            if (gameOpt.isPresent()) {
                Game game = gameOpt.get();
                System.out.println("Jogo encontrado: " + game);

                System.out.print("Digite o novo título (deixe em branco para manter o atual): ");
                String title = scanner.nextLine();
                if (title.isEmpty()) title = game.title();

                System.out.print("Digite o novo gênero (deixe em branco para manter o atual): ");
                String genre = scanner.nextLine();
                if (genre.isEmpty()) genre = game.genre();

                System.out.print("Digite a nova plataforma (deixe em branco para manter a atual): ");
                String platform = scanner.nextLine();
                if (platform.isEmpty()) platform = game.platform();

                System.out.print("Digite a nova data de lançamento (formato YYYY-MM-DD ou deixe em branco para manter a atual): ");
                String releaseDateStr = scanner.nextLine();
                LocalDate releaseDate = releaseDateStr.isEmpty() ? game.releaseDate() : LocalDate.parse(releaseDateStr);

                System.out.print("Digite o novo desenvolvedor (deixe em branco para manter o atual): ");
                String developer = scanner.nextLine();
                if (developer.isEmpty()) developer = game.developer();

                GameDto updatedGame = new GameDto(title, genre, platform, releaseDate, developer);
                controller.updateGame(id, updatedGame);

                System.out.println("Jogo atualizado com sucesso!");
            } else {
                System.out.println("Jogo não encontrado!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o jogo: " + e.getMessage());
        }
    }

    private static void deleteGame() {
        try {
            System.out.print("\nDigite o ID do jogo a ser excluído: ");
            int id = Integer.parseInt(scanner.nextLine());

            Optional<Game> gameOpt = controller.getGameById(id);
            if (gameOpt.isPresent()) {
                controller.deleteGame(id);
                System.out.println("Jogo excluído com sucesso!");
            } else {
                System.out.println("Jogo não encontrado!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao excluir o jogo: " + e.getMessage());
        }
    }
}
