/**
 * @author falvesmac
 */

package br.com.falves.controller;

import br.com.falves.domain.Meta;
import br.com.falves.service.MetaService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/metas")
public class MetaResource {

    private final MetaService service = new MetaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas(){
        try {
            return Response.ok(service.buscarTodasMetas()).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid Meta meta) {
        try {
            service.cadastrarMeta(meta);
            return Response.status(Response.Status.CREATED).entity(meta).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id){
        try {
            Meta m = service.consultarMeta(id);
            return Response.ok(m).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            service.excluirMeta(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}