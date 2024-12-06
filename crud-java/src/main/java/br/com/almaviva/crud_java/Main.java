package main.java.br.com.almaviva.crud_java;

import main.java.br.com.almaviva.crud_java.controller.GameController;
import main.java.br.com.almaviva.crud_java.model.Game;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static GameController controller = new GameController();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===== Gerenciador de Jogos =====");
            System.out.println("1. Adicionar Jogo");
            System.out.println("2. Listar Todos os Jogos");
            System.out.println("3. Busca por ID");
            System.out.println("4. Atualizar Jogo");
            System.out.println("5. Remover Jogo");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> adicionarJogo(scanner);
                case 2 -> listarJogos();
                case 3 -> listarJogoPorId(scanner);
                case 4 -> atualizarJogo(scanner);
                case 5 -> removerJogo(scanner);
                case 6 -> {
                    running = false;
                    System.out.println("Encerrando o programa...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void adicionarJogo(Scanner scanner) throws IOException {
        System.out.println("=== Adicionar Jogo ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Gênero: ");
        String genre = scanner.nextLine();
        System.out.print("Plataforma: ");
        String platform = scanner.nextLine();

        LocalDate releaseDate = null;
        boolean dataValida = false;

        releaseDate = getReleaseDate(dataValida, "Data de Lançamento (yyyy-MM-dd): ", scanner, releaseDate);

        System.out.print("Desenvolvedora: ");
        String developer = scanner.nextLine();

        controller.createGame(title, genre, platform, releaseDate, developer);

        System.out.println("Jogo adicionado: " + title);
    }

    private static void listarJogos() throws IOException {
        System.out.println("=== Lista de Jogos ===");
        var games = controller.getAllGames();
        if (games.isEmpty()) {
            System.out.println("Nenhum jogo encontrado.");
        } else {
            games.forEach(System.out::println);
        }
    }

    private static void listarJogoPorId(Scanner scanner) throws IOException {
        System.out.println("=== Busca por ID ===");
        System.out.print("Digite o ID do jogo que quer buscar: ");
        int idGame = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        var game = controller.getGameById(idGame);
        game.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Jogo não encontrado.")
        );
    }

    private static void atualizarJogo(Scanner scanner) throws IOException {
        System.out.println("=== Atualizar Jogo ===");
        System.out.print("ID do jogo a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        var gameOptional = controller.getGameById(id);
        if (gameOptional.isEmpty()) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        System.out.print("Novo Título: ");
        String newTitle = scanner.nextLine();
        System.out.print("Novo Gênero: ");
        String newGenre = scanner.nextLine();
        System.out.print("Nova Plataforma: ");
        String newPlatform = scanner.nextLine();

        LocalDate newReleaseDate = null;
        boolean dataValida = false;

        newReleaseDate = getReleaseDate(dataValida, "Nova Data de Lançamento (yyyy-MM-dd): ", scanner, newReleaseDate);

        System.out.print("Novo Desenvolvedor: ");
        String newDeveloper = scanner.nextLine();

        Game updatedGame = new Game(id, newTitle, newGenre, newPlatform, newReleaseDate, newDeveloper);
        controller.updateGame(updatedGame);

        System.out.println("Jogo atualizado: " + id);
    }

    private static void removerJogo(Scanner scanner) throws IOException {
        System.out.println("=== Remover Jogo ===");
        System.out.print("ID do jogo a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        var gameOptional = controller.getGameById(id);
        if (gameOptional.isEmpty()) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        controller.deleteGame(id);
        System.out.println("Jogo removido: " + id);
    }

    private static LocalDate getReleaseDate(boolean dataValida, String s, Scanner scanner, LocalDate releaseDate) {
        while (!dataValida) {
            System.out.print(s);
            String dateInput = scanner.nextLine();

            try {
                releaseDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Certifique-se de usar o formato yyyy-MM-dd");
            }
        }
        return releaseDate;
    }
}
