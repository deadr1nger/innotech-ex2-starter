package ru.innotech.starter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "http.logging")
public class LoggingProperties {
    private boolean enabled = true;
}
