package br.com.projeto.handler;

public class RegraNegocioHandler {

    private String mensagem;

    public RegraNegocioHandler() {
    }

    public RegraNegocioHandler(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Error{" +
                "mensagem='" + mensagem + '\'' +
                '}';
    }
}
