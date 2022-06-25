package br.com.alura.videos.repository;

import br.com.alura.videos.modelo.Categoria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    TestEntityManager em;

    @Test
    public void categoriaNaoExistenteErro(){
        String tituloCategoria = "LIVRE";
        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Categoria> categoria = categoriaRepository.findByTituloCategoria(tituloCategoria, paginacao);
        Assert.assertTrue(categoria.isEmpty());
    }

    @Test
    public void carregandoCategoriaPeloTitulo(){
        String tituloCategoria = "LIVRE";
        Categoria livre = new Categoria(tituloCategoria, "Verde");
        em.persist(livre);

        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Categoria> categoria = categoriaRepository.findByTituloCategoria(tituloCategoria, paginacao);
        Assert.assertNotNull(categoria);
        Assert.assertTrue(categoria.getContent().contains(livre));
    }
}