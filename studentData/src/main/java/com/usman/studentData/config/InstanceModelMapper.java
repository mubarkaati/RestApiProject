package com.usman.studentData.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceModelMapper {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}