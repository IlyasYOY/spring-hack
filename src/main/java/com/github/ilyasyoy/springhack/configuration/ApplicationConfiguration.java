package com.github.ilyasyoy.springhack.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public TeamRegisteringBeanDefinitionRegistryPostProcessor teamRegistringBeanDefinitionRegistryPostProcessor(
            Environment environment
    ) {
        return new TeamRegisteringBeanDefinitionRegistryPostProcessor(environment);
    }

    //    @Bean
    public TeamRegisteringBeanFactoryPostProcessor teamRegisteringBeanFactoryPostProcessor(
            Environment environment
    ) {
        return new TeamRegisteringBeanFactoryPostProcessor(environment);
    }
}
