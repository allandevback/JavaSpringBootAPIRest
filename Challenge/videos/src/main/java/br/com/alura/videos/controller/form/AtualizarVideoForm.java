package br.com.alura.videos.controller.form;

import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;
import br.com.alura.videos.repository.CategoriaRepository;
import br.com.alura.videos.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarVideoForm {

    @Length(max = 40)
    private String titulo;
    private String descricao;
    @URL
    private String url;
    private Long categoriaId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video atualizar(Long id, VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        Video video = videoRepository.getById(id);
        if(this.titulo != null){
            video.setTitulo(this.titulo);
        }
        if(this.descricao != null){
            video.setDescricao(this.descricao);
        }
        if(this.url != null){
            video.setUrl(this.url);
        }
        if(this.categoriaId != null){
            Categoria categoria = categoriaRepository.getById(categoriaId);
            video.setCategoria(categoria);
        }
        return video;
    }
}
