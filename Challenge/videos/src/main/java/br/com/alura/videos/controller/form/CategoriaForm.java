package br.com.alura.videos.controller.form;

import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {

    @NotNull @NotEmpty(message = "O título da categoria é obrigatório.")
    private String tituloCategoria;
    @NotNull @NotEmpty(message = "Escolha uma cor para a categoria.")
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

    public Categoria conversorCategoriaForm() {
        return new Categoria(tituloCategoria, cor);
    }
}
