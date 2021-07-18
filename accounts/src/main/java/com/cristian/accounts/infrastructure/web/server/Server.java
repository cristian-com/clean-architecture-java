package com.cristian.accounts.infrastructure.web.server;

import com.cristian.accounts.infrastructure.web.api.members.MemberRegistrationRestController;
import com.cristian.accounts.infrastructure.web.api.members.WebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

@Log
public class Server {

    final InetSocketAddress addr;
    final HttpServer httpServer;
    final int port;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static MemberRegistrationRestController controller;

    public Server(String context, int port) throws IOException {
        this.port = port;
        this.addr = new InetSocketAddress(port);
        this.httpServer = HttpServer.create(addr, 0);
        this.httpServer.createContext(context, new SimpleHandler());
        this.httpServer.setExecutor(Executors.newCachedThreadPool());
    }

    public void run() {
        this.httpServer.start();
    }

    private static class SimpleHandler implements HttpHandler {

        public void handle(HttpExchange exchange) throws IOException {
            final String requestMethod = exchange.getRequestMethod();

            if (requestMethod.equalsIgnoreCase("POST")) {
                var requestBody = objectMapper.readValue(exchange.getRequestBody(),
                        WebRequest.CreateMemberRegistrationRequest.class);

                var response = controller.create(requestBody);

                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, 0);

                OutputStream responseBody = exchange.getResponseBody();
                Headers requestHeaders = exchange.getRequestHeaders();
                Set<String> keySet = requestHeaders.keySet();

                for (String key : keySet) {
                    List<String> values = requestHeaders.get(key);
                    String s = key + " = " + values.toString() + "\n";
                    responseBody.write(s.getBytes());
                }

                responseBody.write("\n".getBytes());

                try {
                    objectMapper.writeValue(responseBody, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.severe(e.getMessage());
                }

                responseBody.close();
            }
        }

    }
}