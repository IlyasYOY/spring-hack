package com.github.ilyasyoy.springhack.configuration;

import com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties;
import com.github.ilyasyoy.springhack.configuration.properties.Team;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.List;

@UtilityClass
public final class RegistrationUtils {

    public static final String EMPLOYEES_BEAN_NAME = "employees";
    public static final String TEAMS_BEAN_NAME = "teams";

}
