package de.performance.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class TestConfiguration {

    private Application application;
    private Tests tests;
    private Browser browser;
    private Map<String, String> testvariables = new HashMap<>();

    @Getter
    @Setter
    @ToString
    public static class Application {
        private String name;
        private String url;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @ToString
    public static class Tests {
        private boolean dryrun = false;
        private String features;
    }


    @Getter
    @Setter
    @ToString
    public static class Browser {
        private String browser;
        private String userprofile;
    }

}