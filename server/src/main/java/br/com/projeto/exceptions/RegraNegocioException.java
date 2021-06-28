package br.com.projeto.exceptions;

import org.json.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RegraNegocioException extends Exception implements ExceptionMapper<RegraNegocioException> {

    private String mensagem;

    public RegraNegocioException(){

    }

    public RegraNegocioException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public Response toResponse(RegraNegocioException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new JSONObject().put("mensagem",e.getMessage()).toString()).build();
    }

    @Override
    public String toString() {
        return "RegraNegocioException{" +
                "mensagem='" + mensagem + '\'' +
                '}';
    }
}
