package com.richert.frameworklessrest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class DatabaseMigrationService {

    private static final java.util.logging.Logger logger = Logger.getLogger("DatabaseMigrationService");

    private DatabaseMigrationService() {
    }

    public static void runMigration() {

        String url = Config.get("DB_URL");
        String user = Config.get("DB_USER");
        String password = Config.get("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Stream<Path> files = Files.list(Paths.get(ClassLoader.getSystemResource("migration").toURI()))) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS migrations (filename VARCHAR PRIMARY KEY);");

            files.filter(p -> p.toString().endsWith(".sql"))
                    .sorted()
                    .forEach(path -> executeStatement(path, connection, statement));

            logger.info("Migration executed.");
        } catch (SQLException | IOException | URISyntaxException e) {
            System.err.println("Migration system failed: " + e.getMessage());
        }
    }

    private static void executeStatement(Path path, Connection connection, Statement stmt) {
        try {
            String filename = path.getFileName().toString();
            PreparedStatement check = connection.prepareStatement("SELECT 1 FROM migrations WHERE filename = ?");
            check.setString(1, filename);
            ResultSet rs = check.executeQuery();
            if (!rs.next()) {
                String sql = new String(Files.readAllBytes(path));
                stmt.execute(sql);
                PreparedStatement insert = connection.prepareStatement("INSERT INTO migrations (filename) VALUES (?)");
                insert.setString(1, filename);
                insert.executeUpdate();
                logger.info("Applied migration: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Migration failed: " + path.getFileName() + " - " + e.getMessage());
        }
    }
}
