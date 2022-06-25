package br.com.alura.videos.repository;

import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;
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
public class VideoRepositoryTest {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    TestEntityManager em;

    @Test
    public void videoNaoExistenteAoBuscarPorTituloErro(){
        String titulo = "Vídeo family friendly";
        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Video> video = videoRepository.findByTitulo(titulo, paginacao);
        Assert.assertTrue(video.isEmpty());
    }

    @Test
    public void buscarVideoPeloTitulo(){
        String titulo = "Vídeo family friendly";
        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Video videoFamilyFriendly = new Video(titulo, "Video feito para crianças",
                "https://localhost8080/videos/1");
        em.persist(videoFamilyFriendly);

        Page<Video> video = videoRepository.findByTitulo(titulo, paginacao);
        Assert.assertNotNull(video);
        Assert.assertTrue(video.getContent().contains(videoFamilyFriendly));
    }

    @Test
    public void videoNaoExistenteAoBuscarPorCategoriaErro(){
        String tituloCategoria = "LIVRE";
        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Categoria livre = new Categoria(tituloCategoria, "Verde");
        em.persist(livre);

        Page<Video> video = videoRepository.findByCategoria_CategoriaId(livre.getCategoriaId(), paginacao);
        Assert.assertTrue(video.isEmpty());
    }

    @Test
    public void buscarVideoPelaCategoria(){
        String tituloCategoria = "LIVRE";
        Categoria livre = new Categoria(tituloCategoria, "Verde");
        em.persist(livre);
        String titulo = "Vídeo family friendly";
        int pagina = 0;
        int tamanho = 1;
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Video videoFamilyFriendly = new Video(titulo, "Video feito para crianças",
                "https://localhost8080/videos/1", livre);
        em.persist(videoFamilyFriendly);

        Page<Video> video = videoRepository.findByCategoria_CategoriaId(livre.getCategoriaId(), paginacao);
        Assert.assertNotNull(video);
        Assert.assertTrue(video.getContent().contains(videoFamilyFriendly));
    }
}