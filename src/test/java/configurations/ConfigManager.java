package configurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private  Properties properties;
    private  String configFilePath = "src/test/java/configurations/config.properties";

    public ConfigManager() {
        try {
            properties = new Properties();

            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);
            fis.close();
        }
        catch(IOException e) {
           
            System.out.println("Error reading");
        }
    }
    public  String get(String fieldName) {
        return properties.getProperty(fieldName);
    }


}
