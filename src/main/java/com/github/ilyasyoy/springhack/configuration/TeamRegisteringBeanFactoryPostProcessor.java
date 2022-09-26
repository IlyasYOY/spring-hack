package com.github.ilyasyoy.springhack.configuration;

import com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties;
import com.github.ilyasyoy.springhack.configuration.properties.Team;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

import static com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties.PERSONNEL_PROPERTIES_PREFIX;

public final class TeamRegisteringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @NonNull
    private final Environment environment;

    public TeamRegisteringBeanFactoryPostProcessor(
            @NonNull Environment environment) {
        Objects.requireNonNull(environment);

        this.environment = environment;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Binder.get(environment)
                .bind(PERSONNEL_PROPERTIES_PREFIX, PersonnelConfigurationProperties.class)
                .ifBound(properties -> {
                    registerEmployees(beanFactory, properties);
                    registerTeams(beanFactory, properties);
                });
    }

    private void registerTeams(ConfigurableListableBeanFactory beanFactory, PersonnelConfigurationProperties properties) {
        List<Team> teams = properties.teams();
        beanFactory.registerSingleton(RegistrationUtils.TEAMS_BEAN_NAME, teams);
        for (Team team : teams) {
            beanFactory.registerSingleton(team.name(), team);
        }
    }

    private void registerEmployees(ConfigurableListableBeanFactory beanFactory, PersonnelConfigurationProperties properties) {
        List<String> employees = properties.employees();
        beanFactory.registerSingleton(RegistrationUtils.EMPLOYEES_BEAN_NAME, employees);
    }
}
