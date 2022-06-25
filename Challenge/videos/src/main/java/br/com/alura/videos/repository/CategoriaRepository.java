package br.com.alura.videos.repository;

import br.com.alura.videos.modelo.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findByTituloCategoria(String tituloCategoria, Pageable paginacao);
}
