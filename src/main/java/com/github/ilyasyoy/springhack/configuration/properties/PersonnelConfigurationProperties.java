package com.github.ilyasyoy.springhack.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@ConstructorBinding
@ConfigurationProperties(prefix = PersonnelConfigurationProperties.PERSONNEL_PROPERTIES_PREFIX)
public record PersonnelConfigurationProperties(@NonNull List<Team> teams, @NotNull List<String> employees) {

    public static final String PERSONNEL_PROPERTIES_PREFIX = "personnel";

    public PersonnelConfigurationProperties(@Nullable List<Team> teams, @Nullable List<String> employees) {
        this.teams = Objects.requireNonNullElse(teams, List.of());
        this.employees = Objects.requireNonNullElse(employees, List.of());
    }
}
