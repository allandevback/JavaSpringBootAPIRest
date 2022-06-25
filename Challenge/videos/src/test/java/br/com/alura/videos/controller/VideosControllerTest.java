package br.com.alura.videos.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VideosControllerTest extends CategoriasControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postarVideoErroCamposVazios() throws Exception {
        URI uri = new URI("/videos");
        String json = "{}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void postarVideo() throws Exception {
        inserirCategoria();
        URI uri = new URI("/videos");
        String json = "{\"titulo\":\"title\", \"descricao\":\"desc\", " +
                "\"url\":\"http://localhost:8080/videos/1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void listarVideos() throws Exception {
        URI uri = new URI("/videos");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void listarVideosPorTitulo() throws Exception {
        postarVideo();
        URI uri = new URI("/videos?titulo=LIVRE");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void detalharVideoNotFound() throws Exception {
        URI uri = new URI("/videos/999");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void detalharVideo() throws Exception {
        postarVideo();
        URI uri = new URI("/videos/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void atualizarVideoNotFound() throws Exception {
        URI uri = new URI("/videos/999");
        String json = "{\"titulo\":\"LIVRE PARA TODOS OS PÚBLICOS\"}";
        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void atualizarVideo() throws Exception {
        postarVideo();
        URI uri = new URI("/videos/1");
        String json = "{\"titulo\":\"LIVRE PARA TODOS OS PÚBLICOS\"}";
        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deletarVideoNotFound() throws Exception {
        URI uri = new URI("/videos/2");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void deletarVideo() throws Exception {
        postarVideo();
        URI uri = new URI("/videos/1");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
}
