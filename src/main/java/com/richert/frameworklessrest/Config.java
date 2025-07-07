package com.richert.frameworklessrest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Files.newInputStream(Paths.get(".env"), StandardOpenOption.READ)) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Config load failed: " + e.getMessage());
        }
    }

    private Config() {
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
