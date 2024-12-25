package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
        }
    }

    /**
     * Retrieves the value for the specified key from the configuration file.
     *
     * @param key The property key
     * @return The property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
