package de.performance.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class TestConfigurationFactory {

    class LevelJsonSerializer extends JsonSerializer<Level> {

        @Override
        public void serialize(Level value, JsonGenerator gen, SerializerProvider serializers) throws IOException, IOException {
            gen.writeString(value.getName());
        }
    }

    class LevelJsonDeserializer extends JsonDeserializer<Level> {

        @Override
        public Level deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String name = p.getText();

            return Level.parse(name);
        }
    }


    private static final Logger LOG = Logger.getLogger(TestConfigurationFactory.class.getName());

    private final static String CONFIG_FILE_NAME = System.getProperty("testconfigfile");
    //private final static String CONFIG_FILE_NAME = "testconfig.yml";

    private static TestConfigurationFactory instance;
    public static String ymlFilePath = "";

    private TestConfiguration appConfig;

    /**
     * Privater Konstruktor des Singletons.
     *
     * @throws IOException Fehler beim Lesen der Konfigurationsdatei.
     */
    private TestConfigurationFactory() throws IOException {
        File file = null;

        ArrayList<File> configFiles = new ArrayList<File>();
        {
            if(System.getProperties().containsKey("testconfigfile")) {
                configFiles.add(new File(System.getProperty("testconfigfile")));
            }
            configFiles.add(new File(TestConfigurationFactory.CONFIG_FILE_NAME));
            configFiles.add(new File(new File("C:/VVC/"), TestConfigurationFactory.CONFIG_FILE_NAME));
            configFiles.add(new File(new File(System.getProperty("user.dir")), TestConfigurationFactory.CONFIG_FILE_NAME));
        };


        for(int i = 0; i<configFiles.size() && file==null; i++) {
            System.out.println("Check configuration file " + configFiles.get(i).getAbsolutePath());
            if(configFiles.get(i).exists()) {
                if(configFiles.get(i).canRead()) {
                    file = configFiles.get(i);
                    System.out.println("Using configuration file " + configFiles.get(i).getAbsolutePath());
                } else {
                    System.out.println("Configuration file not readable " + configFiles.get(i).getAbsolutePath() + " check next!");
                }
            } else {
                System.out.println("Configuration file not existing " + configFiles.get(i).getAbsolutePath() + " check next!");
            }
        }

        if (file != null) {
            // Instantiating a new ObjectMapper as a YAMLFactory
            SimpleModule levelModule = new SimpleModule("LevelModule");
            levelModule.addSerializer(Level.class, new LevelJsonSerializer());
            levelModule.addDeserializer(Level.class, new LevelJsonDeserializer());

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.registerModule(levelModule);
            appConfig = om.readValue(file, TestConfiguration.class);
        } else {
            System.out.println("no configuration file found!");
            System.exit(1);
        }
    }

    /**
     * Liefert das Singleton Objektes.
     *
     * @return Config Instanz
     * @throws IOException Fehler beim erzeugen des Singletons
     */
    public static TestConfigurationFactory instance() throws IOException {
        instance = instance == null ? new TestConfigurationFactory() : instance;
        return instance;
    }

    public TestConfiguration getTestConfiguration() {
        return appConfig;
    }
}
