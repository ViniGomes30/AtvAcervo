package br.vinicius.acervo.repositorio;

import br.vinicius.acervo.entidade.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(String autor);

    List<Livro> findByAnoPublicacao(int anoPublicacao);

    List<Livro> findByTituloContainingIgnoreCase(String termo);

    boolean existsByTituloAndAutor(String titulo, String autor);
}