package config;

import java.io.*;
import java.util.Properties;

public class projectConfig {
    private static final Properties props = new Properties();
    public static final String OPEN_ROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    public static final String NINJA_API_URL = "https://api.api-ninjas.com/v1/animals?name=";

    static {
        // Load from .env file first
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        props.setProperty(parts[0].trim(), parts[1].trim());
                    }
                }
            }
            System.out.println("Loaded configuration from .env file");
        } catch (IOException e) {
            System.out.println("No .env file found, using environment variables only");
        }

        // Load from environment variables (overrides .env)
        loadFromEnv("OPEN_ROUTER_API_KEY", "OPEN_ROUTER_API_KEY");
        //loadFromEnv("OPEN_ROUTER_API_URL", "llm_api.secret");
        loadFromEnv("NINJA_API_KEY", "NINJA_API_KEY");
        //loadFromEnv("NINJA_API_URL", "ninja_api.secret");
    }

    private static void loadFromEnv(String envVar, String propKey) {
        String envValue = System.getenv(envVar);
        if (envValue != null && !envValue.trim().isEmpty()) {
            props.setProperty(propKey, envValue.trim());
        }
    }

    // API Configuration
    public static String getOpenRouterApiKey() {
        return getRequiredValue("OPEN_ROUTER_API_KEY", "OPEN_ROUTER_API_KEY");
    }

    public static String getNinjaApiKey() {
        return getRequiredValue("NINJA_API_KEY", "NINJA_API_KEY");
    }



    private static String getRequiredValue(String propertyKey, String environmentVariable) {
        String value = props.getProperty(propertyKey);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "Configuration missing: " + propertyKey +
                            ". Set environment variable " + environmentVariable +
                            " or add to .env file"
            );
        }
        return value.trim();
    }

    private static String getValue(String propertyKey, String environmentVariable, String defaultValue) {
        String value = props.getProperty(propertyKey);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }
}
