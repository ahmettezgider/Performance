package de.performance.webdriver;

import de.performance.config.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.text.MessageFormat;

public class DriverFactory {

    private WebDriver newChromeDriver(TestConfiguration config) {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(config.getBrowser().getUserprofile()!=null) {
            String userProfileDir = config.getBrowser().getUserprofile();
            options.addArguments(MessageFormat.format("--user-data-dir={0}", userProfileDir));
        }
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        for (String argument : config.getBrowser().getArguments()) {
            options.addArguments(argument);
        }
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    private WebDriver newEdgeDriver(TestConfiguration config) {
        EdgeOptions options = new EdgeOptions();
        if(config.getBrowser().getUserprofile()!=null) {
            String userProfileDir = config.getBrowser().getUserprofile();
            options.addArguments(MessageFormat.format("--user-data-dir={0}", userProfileDir));
        }
        for (String argument : config.getBrowser().getArguments()) {
            options.addArguments(argument);
        }
        WebDriver driver = new EdgeDriver(options);
        return driver;
    }

    private WebDriver newFirefoxDriver(TestConfiguration config) {
        FirefoxOptions options = new FirefoxOptions();
        if(config.getBrowser().getUserprofile() != null) {
            String userProfileDir = config.getBrowser().getUserprofile();
            options.addArguments(MessageFormat.format("--user-data-dir={0}", userProfileDir));
        }
        for (String argument : config.getBrowser().getArguments()) {
            options.addArguments(argument);
        }
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }

    public WebDriver createDriver(TestConfiguration config)  {

        WebDriver driver;
        String browser = config.getBrowser().getBrowser().toLowerCase();
        switch (browser) {
            case "chrome":
                driver = newChromeDriver(config);
                break;
            case "edge":
                driver = newEdgeDriver(config);
                break;
            case "firefox":
                driver = newFirefoxDriver(config);
                break;
            default:
                throw new IllegalArgumentException(MessageFormat.format("browser {0} not supported", browser));
        }
        driver.manage().deleteAllCookies();
        return driver;
    }
}
