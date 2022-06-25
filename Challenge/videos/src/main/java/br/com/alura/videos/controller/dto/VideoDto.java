package br.com.alura.videos.controller.dto;

import br.com.alura.videos.modelo.Video;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
    }

    public static Page<VideoDto> conversor(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
