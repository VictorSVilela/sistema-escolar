package br.com.projeto.handler;

import br.com.projeto.exceptions.RegraNegocioException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RegraNegocioHandler extends Exception implements ExceptionMapper<RegraNegocioHandler> {

    public RegraNegocioHandler(){}

    public RegraNegocioHandler(String mensagem) {
        super(mensagem);
    }

    @Override
    public Response toResponse(RegraNegocioHandler regraNegocioHandler) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new RegraNegocioException(regraNegocioHandler.getMessage())).build();
    }


}
