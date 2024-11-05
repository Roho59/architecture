package com.imt.td.architecture.infrastructures.bdd.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("mongodb.database")
public class MongoProperties {
    private String uri;
    private String name;
}
