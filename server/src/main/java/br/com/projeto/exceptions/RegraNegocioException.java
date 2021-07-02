package br.com.projeto.exceptions;

public class RegraNegocioException {

    private String mensagem;

    public RegraNegocioException() {
    }

    public RegraNegocioException(String mensagem) {
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
