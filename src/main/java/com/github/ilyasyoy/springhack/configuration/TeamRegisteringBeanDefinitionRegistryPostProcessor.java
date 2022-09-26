package com.github.ilyasyoy.springhack.configuration;

import com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties;
import com.github.ilyasyoy.springhack.configuration.properties.Team;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

import static com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties.PERSONNEL_PROPERTIES_PREFIX;

public final class TeamRegisteringBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @NonNull
    private final Environment environment;

    public TeamRegisteringBeanDefinitionRegistryPostProcessor(
            @NonNull Environment environment
    ) {
        Objects.requireNonNull(environment);

        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Binder.get(environment)
                .bind(PERSONNEL_PROPERTIES_PREFIX, PersonnelConfigurationProperties.class)
                .ifBound(properties -> {
                    registerEmployees(registry, properties);
                    registerTeams(registry, properties);
                });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private void registerTeams(BeanDefinitionRegistry registry, PersonnelConfigurationProperties properties) {
        List<Team> teams = properties.teams();
        AbstractBeanDefinition teamsBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(List.class, () -> teams)
                .getBeanDefinition();
        registry.registerBeanDefinition(RegistrationUtils.TEAMS_BEAN_NAME, teamsBeanDefinition);

        for (Team team : teams) {
            AbstractBeanDefinition teamBeanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(Team.class, () -> team)
                    .getBeanDefinition();
            registry.registerBeanDefinition(team.name(), teamBeanDefinition);
        }
    }

    private void registerEmployees(BeanDefinitionRegistry registry, PersonnelConfigurationProperties properties) {
        List<String> employees = properties.employees();
        AbstractBeanDefinition employeesBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(List.class, () -> employees)
                .getBeanDefinition();
        registry.registerBeanDefinition(RegistrationUtils.EMPLOYEES_BEAN_NAME, employeesBeanDefinition);
    }

}
