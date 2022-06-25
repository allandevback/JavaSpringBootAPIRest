package br.com.alura.videos.controller.form;

import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;
import br.com.alura.videos.repository.CategoriaRepository;
import br.com.alura.videos.repository.VideoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarCategoriaForm {

    private String tituloCategoria;
    private String cor;

    public String getTituloCategoria() {
        return tituloCategoria;
    }

    public void setTituloCategoria(String tituloCategoria) {
        this.tituloCategoria = tituloCategoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria atualizarCategoria(Long categoriaId, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getById(categoriaId);
        if(this.tituloCategoria != null){
            categoria.setTituloCategoria(this.tituloCategoria);
        }
        if(this.cor != null){
            categoria.setCor(this.cor);
        }
        return categoria;
    }
}
