package br.com.projeto.exceptions;

import br.com.projeto.handler.RegraNegocioHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RegraNegocioException extends Exception implements ExceptionMapper<RegraNegocioException> {

    public RegraNegocioException(){}

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

    @Override
    public Response toResponse(RegraNegocioException regraNegocioException) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new RegraNegocioHandler(regraNegocioException.getMessage())).build();
    }


}
