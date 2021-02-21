package controller;


import lombok.extern.java.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
@Log
public class Main {

    public static final int PORT = 9090;


    public static void main(String[] args) {
        new Main().main();
    }

    public void main() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            Listen listen = new Listen(server);
            listen.start();
            log.info("Estoy en la main del server, escuchando la llegada de un cliente...");
        } catch (IOException e) {
            log.warning(e.getLocalizedMessage());
        }
    }
}
