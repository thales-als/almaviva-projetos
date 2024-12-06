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
                case 3 -> listarJogoPorId();
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

        while (!dataValida) {
            System.out.print("Data de Lançamento (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            try {
                releaseDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Certifique-se de usar o formato yyyy-MM-dd");
            }
        }

        System.out.print("Desenvolvedora: ");
        String developer = scanner.nextLine();

        controller.createGame(title, genre, platform, releaseDate, developer);

        System.out.println("Jogo adicionado: " + title);
    }

    private static void listarJogos() throws IOException {
        System.out.println("=== Lista de Jogos ===");
        // Substitua com a chamada ao método do controller

        System.out.println("Exibindo jogos...");

        System.out.println(controller.getAllGames());
    }

    private static void listarJogoPorId() throws IOException {
        System.out.println("=== Busca por ID ===");

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do jogo que quer buscar: ");
        int idGame = sc.nextInt();


        System.out.println("Exibindo jogos...");



        System.out.println(controller.getGameById(idGame));
    }

    private static void atualizarJogo(Scanner scanner) {
        System.out.println("=== Atualizar Jogo ===");
        System.out.print("ID do jogo a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Novo Título: ");
        String novoTitulo = scanner.nextLine();
        // Continue pedindo os campos necessários

        // Substitua com a chamada ao método do controller
        System.out.println("Jogo atualizado: " + id);
    }

    private static void removerJogo(Scanner scanner) {
        System.out.println("=== Remover Jogo ===");
        System.out.print("ID do jogo a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Substitua com a chamada ao método do controller
        System.out.println("Jogo removido: " + id);
    }
}
