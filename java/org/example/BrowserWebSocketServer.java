package org.example;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;

public class BrowserWebSocketServer extends WebSocketServer {

    public BrowserWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection opened");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received domain: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    public static void main(String[] args) {
        WebSocketServer server = new BrowserWebSocketServer(new InetSocketAddress("localhost", 8080));
        server.start();
        System.out.println("Server started on port: " + server.getPort());
    }
}