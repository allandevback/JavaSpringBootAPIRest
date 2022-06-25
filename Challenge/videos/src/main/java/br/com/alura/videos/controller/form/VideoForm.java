package br.com.alura.videos.controller.form;

import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;
import br.com.alura.videos.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VideoForm {

    @NotEmpty @NotNull @Length(max = 40)
    private String titulo;
    @NotEmpty @NotNull
    private String descricao;
    @URL @NotEmpty @NotNull
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

    public Video conversor(CategoriaRepository categoriaRepository) {
        if(categoriaId != null){
            Categoria categoria = categoriaRepository.getById(categoriaId);
            return new Video(titulo, descricao, url, categoria);
        }
        Categoria categoria = categoriaRepository.getById(1L);
        return new Video(titulo, descricao, url, categoria);
    }
}
