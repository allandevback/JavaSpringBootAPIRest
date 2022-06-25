package br.com.alura.videos.config.validacao;

public class ErroFormularioDto {

    private String campoErrado;
    private String mensagemErro;

    public ErroFormularioDto(String campoErrado, String mensagemErro) {
        this.campoErrado = campoErrado;
        this.mensagemErro = mensagemErro;
    }

    public String getCampoErrado() {
        return campoErrado;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
