package br.vinicius.acervo.aplicacao;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.vinicius.acervo.entidade.Biblioteca;
import br.vinicius.acervo.entidade.Livro;
import br.vinicius.acervo.repositorio.BibliotecaRepository;
import br.vinicius.acervo.repositorio.LivroRepository;

@Component
public class ConsoleApp {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarPorAutor();
                    break;
                case 4:
                    buscarPorAno();
                    break;
                case 5:
                    buscarPorTermoTitulo();
                    break;
                case 6:
                    cadastrarBiblioteca();
                    break;
                case 7:
                    listarBibliotecas();
                    break;
                case 8:
                    associarLivroBiblioteca();
                    break;
                case 9:
                    listarLivrosBiblioteca();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Listar Livros");
        System.out.println("3. Buscar por Autor");
        System.out.println("4. Buscar por Ano");
        System.out.println("5. Buscar por Termo no Título");
        System.out.println("6. Cadastrar Biblioteca");
        System.out.println("7. Listar Bibliotecas");
        System.out.println("8. Associar Livro a Biblioteca");
        System.out.println("9. Listar Livros de uma Biblioteca");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarLivro() {
        System.out.println("[Cadastro de Livro]");
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ano de publicação: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a editora: ");
        String editora = scanner.nextLine();
        if (livroRepository.existsByTituloAndAutor(titulo, autor)) {
            System.out.println("Já existe um livro cadastrado com esse título e autor.");
        } else {
            Livro livro = new Livro(titulo, autor, ano, editora);
            livroRepository.save(livro);
            System.out.println("Livro cadastrado com sucesso!");
        }

    }

    private void listarLivros() {
        System.out.println("[Listagem Completa do Acervo]");
        System.out.println("ID | Título | Autor | Ano | Editora");
        System.out.println("-------------------------------------------------------------------");
        List<Livro> livros = livroRepository.findAll();
        for (Livro livro : livros) {
            System.out.println(livro.getId() + " | " + livro.getTitulo() + " | " + livro.getAutor() + " | "
                    + livro.getAnoPublicacao() + " | " + livro.getEditora());
        }
    }

    private void buscarPorAutor() {
        System.out.print("[Busca por Autor]\nDigite o nome do autor: ");
        String autor = scanner.nextLine();
        List<Livro> livros = livroRepository.findByAutor(autor);
        System.out.println("Livros encontrados:");
        livros.forEach(System.out::println);
    }

    private void buscarPorAno() {
        System.out.print("[Busca por Ano de Publicação]\nDigite o ano desejado: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        List<Livro> livros = livroRepository.findByAnoPublicacao(ano);
        System.out.println("Livros publicados em " + ano + ":");
        livros.forEach(System.out::println);
    }

    private void buscarPorTermoTitulo() {
        System.out.print("[Busca por Termo no Título]\nDigite o termo desejado: ");
        String termo = scanner.nextLine();
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(termo);
        System.out.println("Livros encontrados:");
        livros.forEach(System.out::println);
    }

    private void cadastrarBiblioteca() {
        System.out.println("[Cadastro de Biblioteca]");
        System.out.print("Digite o nome da biblioteca: ");
        String nome = scanner.nextLine();
        Biblioteca biblioteca = new Biblioteca(nome);
        bibliotecaRepository.save(biblioteca);
        System.out.println("Biblioteca cadastrada com sucesso!");
    }

    private void listarBibliotecas() {
        System.out.println("[Listagem de Bibliotecas]");
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        for (Biblioteca biblioteca : bibliotecas) {
            System.out.println("ID: " + biblioteca.getId() + " | Nome: " + biblioteca.getNome());
        }
    }

    private void associarLivroBiblioteca() {
        System.out.println("[Associar Livro a Biblioteca]");
        System.out.print("Digite o ID da biblioteca: ");
        Long bibliotecaId = scanner.nextLong();
        scanner.nextLine();
        Optional<Biblioteca> bibliotecaOpt = bibliotecaRepository.findById(bibliotecaId);

        if (bibliotecaOpt.isPresent()) {
            Biblioteca biblioteca = bibliotecaOpt.get();

            // Initialize the livros collection
            biblioteca.getLivros().size(); // Forces initialization of the collection

            System.out.print("Digite o ID do livro: ");
            Long livroId = scanner.nextLong();
            scanner.nextLine();
            Optional<Livro> livroOpt = livroRepository.findById(livroId);

            if (livroOpt.isPresent()) {
                Livro livro = livroOpt.get();
                biblioteca.adicionarLivro(livro);
                bibliotecaRepository.save(biblioteca);
                System.out.println("Livro associado à biblioteca com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }
        } else {
            System.out.println("Biblioteca não encontrada.");
        }
    }

    private void listarLivrosBiblioteca() {
        System.out.println("[Listar Livros de uma Biblioteca]");
        System.out.print("Digite o ID da biblioteca: ");
        Long bibliotecaId = scanner.nextLong();
        scanner.nextLine();
        Optional<Biblioteca> bibliotecaOpt = bibliotecaRepository.findById(bibliotecaId);

        if (bibliotecaOpt.isPresent()) {
            Biblioteca biblioteca = bibliotecaOpt.get();
            System.out.println("Livros da biblioteca " + biblioteca.getNome() + ":");
            biblioteca.getLivros().forEach(System.out::println);
        } else {
            System.out.println("Biblioteca não encontrada.");
        }
    }
}