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
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void inserirCategoria() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"tituloCategoria\":\"LIVRE\", \"cor\":\"Verde\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void inserirCategoriaErroCampoVazio() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void listarCategorias() throws Exception {
        URI uri = new URI("/categorias");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void listarCategoriasPorTitulo() throws Exception {
        inserirCategoria();
        URI uri = new URI("/categorias?tituloCategoria=LIVRE");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void listarVideosPorCategoria() throws Exception {
        URI uri = new URI("/categorias/1/videos");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void detalharCategoria() throws Exception {
        inserirCategoria();
        URI uri = new URI("/categorias/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void detalharCategoriaNotFound() throws Exception {
        URI uri = new URI("/categorias/999");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void atualizarCategoria() throws Exception {
        inserirCategoria();
        URI uri = new URI("/categorias/1");
        String json = "{\"tituloCategoria\":\"LIVRE PARA TODOS OS PÚBLICOS\"}";
        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void atualizarCategoriaNotFound() throws Exception {
        URI uri = new URI("/categorias/999");
        String json = "{\"tituloCategoria\":\"LIVRE PARA TODOS OS PÚBLICOS\"}";
        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void deletarCategoria() throws Exception {
        inserirCategoria();
        URI uri = new URI("/categorias/1");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deletarCategoriaNotFound() throws Exception {
        URI uri = new URI("/categorias/999");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }
}
