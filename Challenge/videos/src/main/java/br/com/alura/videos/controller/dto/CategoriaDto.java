package br.com.alura.videos.controller.dto;

import br.com.alura.videos.modelo.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private Long categoriaId;
    private String tituloCategoria;
    private String cor;

    public CategoriaDto(Categoria categoria){
        this.categoriaId = categoria.getCategoriaId();
        this.tituloCategoria = categoria.getTituloCategoria();
        this.cor = categoria.getCor();
    }

    public static Page<CategoriaDto> conversorCategoria(Page<Categoria> categorias){
        return categorias.map(CategoriaDto::new);
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

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
}
