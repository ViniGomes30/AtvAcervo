package br.vinicius.acervo.aplicacao;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.vinicius.acervo.dominio.Livro;

public class ConsoleApp {
    private List<Livro> livros = new ArrayList<>();

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
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        Livro livro = new Livro(titulo, autor, ano);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("Lista de livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private void buscarPorAutor(Scanner scanner) {
        System.out.print("Digite o autor: ");
        String autor = scanner.nextLine();
        List<Livro> resultados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(livro);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : resultados) {
                System.out.println(livro);
            }
        }
    }

    private void buscarPorAno(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        List<Livro> resultados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getAno() == ano) {
                resultados.add(livro);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o ano: " + ano);
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : resultados) {
                System.out.println(livro);
            }
        }
    }

    private void buscarPorTitulo(Scanner scanner) {
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        List<Livro> resultados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(livro);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o título: " + titulo);
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : resultados) {
                System.out.println(livro);
            }
        }
    }
}