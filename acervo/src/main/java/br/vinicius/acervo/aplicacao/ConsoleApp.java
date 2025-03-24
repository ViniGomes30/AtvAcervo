package br.vinicius.acervo.aplicacao;
import org.springframework.stereotype.Component;
import java.util.Scanner;

public class ConsoleApp {
    public void iniciar() {
        System.out.println("Aplicação console iniciada!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Buscar por ano");
            System.out.println("5. Buscar por título");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro(scanner);
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarPorAutor(scanner);
                    break;
                case 4:
                    buscarPorAno(scanner);
                    break;
                case 5:
                    buscarPorTitulo(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private void cadastrarLivro(Scanner scanner) {
        System.out.println("Cadastrar livro - Em desenvolvimento");
    }

    private void listarLivros() {
        System.out.println("Listar livros - Em desenvolvimento");
    }

    private void buscarPorAutor(Scanner scanner) {
        System.out.println("Buscar por autor - Em desenvolvimento");
    }

    private void buscarPorAno(Scanner scanner) {
        System.out.println("Buscar por ano - Em desenvolvimento");
    }

    private void buscarPorTitulo(Scanner scanner) {
        System.out.println("Buscar por título - Em desenvolvimento");
    }
}