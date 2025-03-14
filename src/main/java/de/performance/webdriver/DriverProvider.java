package de.performance.webdriver;

import de.performance.config.TestConfiguration;
import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static final ThreadLocal<WebDriver> driverImpl = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<TestConfiguration> configs = new ThreadLocal<TestConfiguration>();

    private static String browser=null;
    private static String browserDriverVersion = null;

    public static String getBrowser() {
        return DriverProvider.browser;
    }

    public static void setBrowser(String browser) {
        DriverProvider.browser = browser;
    }

    public static String getBrowserDriverVersion() {
        return DriverProvider.browserDriverVersion;
    }

    public static void setBrowserDriverVersion(String browserDriverVersion) {
        DriverProvider.browserDriverVersion = browserDriverVersion;
    }


    private DriverProvider() {
    }

    public static void configure(TestConfiguration config) {
        configs.set(config);
    }


    public static WebDriver getDriver() {
        if(driverImpl.get() == null) {
            DriverFactory driverFactory = new DriverFactory();
            WebDriver driver = driverFactory.createDriver(configs.get());
            driverImpl.set(driver);
        }
        return driverImpl.get();
    }

    public static void quit() {
        if(driverImpl.get() != null) {
            driverImpl.get().quit();
            driverImpl.set(null);
        }
    }
}
