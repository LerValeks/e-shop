package eshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBPropertiesReader {

    private static final Logger LOGGER = Logger.getLogger("eshop.util.jdbc.DBPropertiesReader");
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";
    // private Properties properties = new Properties();

    public static Properties loadDBProperties() {
        Properties prop = new Properties();
        try (InputStream input = DBPropertiesReader.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE_NAME)) {
            prop.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to read properties file: " + DB_PROPERTIES_FILE_NAME);
        }
        return prop;
    }
}