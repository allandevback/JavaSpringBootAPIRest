package br.com.alura.videos.repository;

import br.com.alura.videos.controller.dto.VideoDtoDetalhado;
import br.com.alura.videos.modelo.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    //Query pra caso o nome do método fique ruim
    //@Query("SELECT t FROM Video t WHERE t.titulo = :titulo")
    Page<Video> findByTitulo(/*@Param("titulo")*/ String titulo, Pageable paginacao);
    Page<Video> findByCategoria_CategoriaId(/*@Param("categoria_id")*/ Long categoriaId, Pageable paginacao);
}
