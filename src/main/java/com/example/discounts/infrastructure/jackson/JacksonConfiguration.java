package com.example.discounts.infrastructure.jackson;

import com.example.discounts.infrastructure.jackson.deserialization.UUIDDeserializer;
import com.example.discounts.infrastructure.jackson.serialization.UUIDSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addSerializer(UUID.class, new UUIDSerializer());
        module.addDeserializer(UUID.class, new UUIDDeserializer());
        mapper.registerModule(module);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }
}
