/**
 * @author falvesmac
 */

package br.com.falves;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class App {
    public static final String BASE_URI = "http://0.0.0.0:8080/";

    public static void main(String[] args) {
        final ResourceConfig rc = new ResourceConfig()
                .packages("br.com.falves.controller")
                // 1. Registro explícito do suporte a JSON (evita o erro 415)
                .register(org.glassfish.jersey.jackson.JacksonFeature.class);

        // 2. Mantenha o CORS aqui OU no CorsFilter.java (recomendo manter aqui para garantir)
        rc.register((jakarta.ws.rs.container.ContainerResponseFilter) (requestContext, responseContext) -> {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

            // 3. O SEGREDO PARA O POST FUNCIONAR: Responder 200 OK para o navegador no teste OPTIONS
            if (requestContext.getMethod().equals("OPTIONS")) {
                responseContext.setStatus(200);
            }
        });

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
}