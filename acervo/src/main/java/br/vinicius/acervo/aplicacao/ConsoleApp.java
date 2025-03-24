package br.vinicius.acervo.aplicacao;
import br.vinicius.acervo.entidade.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import br.vinicius.acervo.repositorio.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp {

    @Autowired
    private LivroRepository livroRepository;

    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

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
        if (livroRepository.existsByTituloAndAutor(titulo, autor)){
            System.out.println("Já existe um livro cadastrado com esse título e autor.");
        }else {
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
            System.out.println(livro.getId() + " | " + livro.getTitulo() + " | " + livro.getAutor() + " | " + livro.getAnoPublicacao() + " | " + livro.getEditora());
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
    }