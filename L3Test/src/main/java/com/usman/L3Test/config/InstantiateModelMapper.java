package com.usman.L3Test.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstantiateModelMapper {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}