package com.github.ilyasyoy.springhack.configuration.properties;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

public record Team(@NonNull String name, @NonNull List<String> involvedEmployees) {
    public Team(@NonNull String name, @Nullable List<String> involvedEmployees) {
        Objects.requireNonNull(name);

        this.name = name;
        this.involvedEmployees = Objects.requireNonNullElse(involvedEmployees, List.of());
    }
}
