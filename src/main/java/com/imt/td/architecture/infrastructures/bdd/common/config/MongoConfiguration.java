package com.imt.td.architecture.infrastructures.bdd.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.imt.td.architecture.infrastructures.bdd")
public class MongoConfiguration {
    
    @Bean
    public MongoClient mongo(final MongoProperties properties) {
        return MongoClients.create(
                MongoClientSettings.builder()
                                   .uuidRepresentation(UuidRepresentation.STANDARD)
                                   .applyConnectionString(new ConnectionString(properties.getUri()))
                                   .build()
        );
    }
    
    @Bean
    public MongoTemplate mongoTemplate(final MongoProperties properties, final MongoClient client){
        return new MongoTemplate(client, properties.getName());
    }
}
