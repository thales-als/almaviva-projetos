package main.java.br.com.almaviva.crud;

import java.util.Scanner;
import java.util.UUID;

import main.java.br.com.almaviva.crud.controller.GameController;
import main.java.br.com.almaviva.crud.model.Game;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Criar Jogo");
            System.out.println("2. Listar todos os jogos");
            System.out.println("3. Buscar jogo por ID");
            System.out.println("4. Atualizar jogo por ID");
            System.out.println("5. Remover todos os jogos");
            System.out.println("6. Remover jogo por ID");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida!");
                scanner.next();
                continue;
            }
            
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> createGame(gameController, scanner);
                case 2 -> listAllGames(gameController);
                case 3 -> findGameById(gameController, scanner);
                case 4 -> updateGameById(gameController, scanner);
                case 5 -> removeAllGames(gameController);
                case 6 -> removeGameById(gameController, scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void createGame(GameController gameController, Scanner scanner) {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Gênero: ");
        String genre = scanner.nextLine();
        System.out.print("Plataforma: ");
        String platform = scanner.nextLine();
        System.out.print("Data de lançamento (DD/MM/YYYY): ");
        String releaseDate = scanner.nextLine();
        System.out.print("Desenvolvedor: ");
        String developer = scanner.nextLine();
        gameController.createGame(new Game(title, genre, platform, releaseDate, developer));
    }

    private static void listAllGames(GameController gameController) {
        gameController.listAllGames();
    }

    private static void findGameById(GameController gameController, Scanner scanner) {
        System.out.print("ID do jogo: ");
        UUID id = UUID.fromString(scanner.nextLine());
        gameController.findGameById(id);
    }

    private static void updateGameById(GameController gameController, Scanner scanner) {
        System.out.print("ID do jogo: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("Título (deixe vazio para manter o atual): ");
        String title = scanner.nextLine();
        System.out.print("Gênero (deixe vazio para manter o atual): ");
        String genre = scanner.nextLine();
        System.out.print("Plataforma (deixe vazio para manter o atual): ");
        String platform = scanner.nextLine();
        System.out.print("Data de lançamento (deixe vazio para manter o atual): ");
        String releaseDate = scanner.nextLine();
        System.out.print("Desenvolvedor (deixe vazio para manter o atual): ");
        String developer = scanner.nextLine();
        
        Game updatedGame = new Game(title, genre, platform, releaseDate, developer);
        gameController.updateGameById(id, updatedGame);
    }

    private static void removeAllGames(GameController gameController) {
        gameController.removeAllGames();
    }

    private static void removeGameById(GameController gameController, Scanner scanner) {
        System.out.print("ID do jogo: ");
        UUID id = UUID.fromString(scanner.nextLine());
        gameController.removeGameById(id);
    }
}