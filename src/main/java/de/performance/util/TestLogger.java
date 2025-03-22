package de.performance.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.*;


public class TestLogger {

    private static Logger logger;

    public static void setLogger(Class clazz) {
        logger =  Logger.getLogger(clazz.getName());
        //logger.setLevel(Level.CONFIG);
        logger.setUseParentHandlers(false);
        FileHandler fileHandler = null;
        try {
            InputStream configFile = new FileInputStream("logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
            fileHandler = new FileHandler("log.log", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public static void logWrite(String msg){
        if (logger == null){
            setLogger(TestLogger.class);
        }
        logger.info(msg);
    }

}
