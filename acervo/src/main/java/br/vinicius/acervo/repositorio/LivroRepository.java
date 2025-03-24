package br.vinicius.acervo.repositorio;

import br.vinicius.acervo.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);

    List<Livro> findByAnoPublicacao(int anoPublicacao);

    List<Livro> findByTituloIgnoreCaseContaining(String termo);

    boolean existsByTituloAndAutor(String titulo, String autor);
}
