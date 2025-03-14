package de.performance.tests;

import de.performance.config.TestConfiguration;
import de.performance.config.TestConfigurationFactory;
import org.junit.runner.JUnitCore;

import java.io.IOException;


public class Runner {

    public static void main(String[] args) throws Exception {
        System.setProperty("cucumber.publish.quiet", "true");
        if (args.length > 0) {
            String configFile = args[0];
            System.setProperty("testconfigfile", configFile);
        } else if (!System.getProperties().containsKey("testconfigfile")) {
            System.setProperty("testconfigfile", "testconfig.yml");
        }

        configureCucumber(args);
        runTests();
        System.exit(0);
    }

    private static void runTests() throws IOException {
        JUnitCore junit = new JUnitCore();
        long start = System.currentTimeMillis();
        junit.run(Tests.class);
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
    }

    private static void configureCucumber(String[] args) throws IOException {
        System.setProperty("cucumber.publish.quiet", "true");
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
        String dryRun = config.getTests().isDryrun() ? "true" : "false";
        System.setProperty("cucumber.execution.dry-run", dryRun.trim());
        String features = config.getTests().getFeatures();
        if (features != null && features.trim().length() > 0) {
            System.setProperty("cucumber.features", features);
        }
    }

}
