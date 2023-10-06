package com.oneaston.tap.automation.ui.utils.dataprovider;

import org.testng.Assert;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {

    private Properties prop;

    private TestProperties() {
        loadSystemPropertiesFromFile();
    }

    public static synchronized TestProperties getInstance() {
        return new TestProperties();
    }

    /**
     *
     */
    private void loadSystemPropertiesFromFile() {
        if (prop == null) {
            prop = new Properties();
            try {
                FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                        + "/src/test/resources/configurations/property_files/ProjectConfig.properties");
                prop.load(file);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return System.getProperty(key) != null ? System.getProperty(key) : prop.getProperty(key);
    }

}
