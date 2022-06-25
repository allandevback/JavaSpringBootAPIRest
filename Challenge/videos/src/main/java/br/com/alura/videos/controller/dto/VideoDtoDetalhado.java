package br.com.alura.videos.controller.dto;

import br.com.alura.videos.modelo.Video;

public class VideoDtoDetalhado extends VideoDto{

    private String url;
    private Long categoriaId;
    private String tituloCategoria;
    private String cor;

    public VideoDtoDetalhado(Video video) {
        super(video);
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getCategoriaId();
        this.tituloCategoria = video.getCategoria().getTituloCategoria();
        this.cor = video.getCategoria().getCor();
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
