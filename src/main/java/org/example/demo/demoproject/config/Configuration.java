package org.example.demo.demoproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "config")
public class Configuration {
    private String coinCapUrl;
}
