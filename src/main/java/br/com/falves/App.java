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
        final ResourceConfig rc = new ResourceConfig().packages("br.com.falves.controller");

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        System.out.println(String.format("Servidor rodando em %s\nAperte CTRL+C para parar...", BASE_URI));
    }
}