package com.svalero.ERM;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmergencyReportingSystemConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
