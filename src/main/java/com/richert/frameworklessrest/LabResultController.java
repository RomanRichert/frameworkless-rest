package com.richert.frameworklessrest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

class LabResultController implements HttpHandler {

    private final LabResultService service = LabResultService.getInstance();

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void handle(@NotNull HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        switch (method) {
            case "POST":
                createLabResult(exchange);
                break;
            case "GET":
                getLabResults(exchange);
                break;
            default:
                exchange.sendResponseHeaders(405, -1);
        }
    }

    private void getLabResults(@NotNull HttpExchange exchange) throws IOException {
        logger.info("GET /labresults called");
        String query = exchange.getRequestURI().getQuery();
        List<LabResult> results;

        if (query != null && query.startsWith("patientId=")) {
            String patientId = query.split("=")[1];
            try {
                results = service.getResultsByPatientId(patientId);
            } catch (SQLException e) {
                logger.warning("Error occurred while getting LabResults by patient ID: " + e.getMessage());
                respond(exchange, 500, "Database error: " + e.getMessage());
                return;
            }
        } else {
            try {
                logger.info("Trying to fetch results...");
                results = service.getAllResults();
                logger.info("Results fetched: " + results.size());
            } catch (SQLException e) {
                logger.warning("Error occurred while getting all LabResults: " + e.getMessage());
                respond(exchange, 500, "Database error: " + e.getMessage());
                return;
            }
        }

        JsonArrayBuilder array = Json.createArrayBuilder();
        for (LabResult r : results) {
            logger.info("Mapping result to JSON: " + r);
            array.add(LabResultMapper.toJson(r));
        }

        String response = array.build().toString();
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private void createLabResult(@NotNull HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        JsonReader reader = Json.createReader(is);
        JsonObject inputJson;
        try {
            inputJson = reader.readObject();
        } catch (Exception e) {
            logger.warning("Error occurred while reading request to create new LabResult" + e.getMessage());
            respond(exchange, 400, "Invalid JSON");
            return;
        }

        LabResultDTO dto = new LabResultDTO(
                inputJson.getString("patientId", null),
                inputJson.getJsonNumber("result").doubleValue(),
                inputJson.getString("unit", null)
        );

        if (!dto.isValid()) {
            respond(exchange, 400, "Missing or invalid fields");
            return;
        }

        try {
            service.saveLabResult(dto);
            respond(exchange, 200, "Lab result saved.");
        } catch (Exception e) {
            logger.warning("Error occurred while saving new LabResult" + e.getMessage());
            respond(exchange, 500, "Database error: " + e.getMessage());
        }
    }

    private void respond(@NotNull HttpExchange exchange, int status, String message) throws IOException {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}