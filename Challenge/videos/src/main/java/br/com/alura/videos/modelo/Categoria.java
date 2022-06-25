package br.com.alura.videos.modelo;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    @NotNull @NotEmpty(message = "O título da categoria é obrigatório.")
    private String tituloCategoria;
    @NotNull @NotEmpty(message = "Escolha uma cor para a categoria.")
    private String cor;

    public Categoria(){

    }

    public Categoria(String tituloCategoria, String cor) {
        this.tituloCategoria = tituloCategoria;
        this.cor = cor;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId == null ? 1L : categoriaId;
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
