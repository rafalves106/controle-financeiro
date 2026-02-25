/**
 * @author falvesmac
 */

package br.com.falves.controller;

import br.com.falves.domain.Movimentacao;
import br.com.falves.service.MovimentacaoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/movimentacoes")
public class MovimentacaoResource {
    private final MovimentacaoService service = new MovimentacaoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas(){
        try {
            return Response.ok(service.buscarTodasMovimentacoes()).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id){
        try {
            Movimentacao m = service.consultarMovimentacao(id);
            return Response.ok(m).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid br.com.falves.domain.Movimentacao movimentacao) {
        try {
            service.cadastrarMovimentacao(movimentacao);
            return Response.status(Response.Status.CREATED).entity(movimentacao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editar(@PathParam("id") Long idOriginal, @Valid Movimentacao movimentacaoAlterada) {
        try {
            service.alterarMovimentacao(idOriginal, movimentacaoAlterada);
            return Response.ok(Response.Status.OK).entity(movimentacaoAlterada).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            service.excluirMovimentacao(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}