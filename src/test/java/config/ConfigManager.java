package config;

import java.io.InputStream;
import java.util.Properties;    

public class ConfigManager {
    private static final Properties properties = new Properties();

    // Static block runs once when class is loaded
    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            //Above will automatically detect resources folder and read data of config.properties
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    // Get any property by key
public static String get(String key) {
    String systemValue = System.getProperty(key);
    if (systemValue != null) return systemValue;

    String envValue = System.getenv(key.replace('.', '_').toUpperCase());
    if (envValue != null) return envValue;

    return properties.getProperty(key);
}

    // Optional: get with default
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}