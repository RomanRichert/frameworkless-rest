package com.richert.frameworklessrest;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static com.richert.frameworklessrest.DatabaseMigrationService.runMigration;

public class Main {

    private static final Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/labresults", new LabResultController());
        server.setExecutor(Executors.newFixedThreadPool(4));
        runMigration();
        server.start();
        logger.info("Microservice started at http://localhost:8080/labresults");
    }
}