package org.samodumkina.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application")
public record ApplicationProperties(int productServerPort) {

}
