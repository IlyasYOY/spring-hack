package com.github.ilyasyoy.springhack.configuration;

import com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        PersonnelConfigurationProperties.class
})
public class ConfigurationPropertiesConfiguration {
}
