package com.github.ilyasyoy.springhack;

import com.github.ilyasyoy.springhack.configuration.properties.PersonnelConfigurationProperties;
import com.github.ilyasyoy.springhack.configuration.properties.Team;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SpringHackApplicationTests {

    public static final String ILIA = "Ilia";
    public static final String PETER = "Peter";
    public static final String JIM = "Jim";

    @Test
    void contextLoads(@Autowired ApplicationContext applicationContext) {
        assertNotNull(applicationContext);
    }

    @Nested
    class BeansTest {

        @Autowired
        ApplicationContext context;

        @Test
        void contextContainsTeamBeans() {
            assertAll(
                    () -> assertDoesNotThrow(() -> context.getBean("employees")),
                    () -> assertDoesNotThrow(() -> context.getBean("teams")),
                    () -> assertDoesNotThrow(() -> context.getBean("Super Cool"))
            );
        }

        @Test
        void contextContainsTeamBeansAutowirable(
                @Qualifier("teams") @Autowired List<Team> teams,
                @Qualifier("employees") @Autowired List<String> employees,
                @Qualifier("Super Cool") @Autowired Team superCoolTeam
        ) {
            assertAll(
                    () -> assertNotNull(teams),
                    () -> assertNotNull(employees),
                    () -> assertNotNull(superCoolTeam)
            );
        }

    }

    @Nested
    class PropertiesTest {
        @Autowired
        PersonnelConfigurationProperties properties;

        @Test
        @Disabled
        void contextContainsTeamProperties() {
            assertAll(
                    () -> assertTrue(properties.teams().isEmpty()),
                    () -> assertTrue(properties.employees().isEmpty())
            );
        }

        @Test
        void contextShouldHaveFilledProperties() {
            assertAll(() -> {
                List<String> employees = properties.employees();
                assertAll(
                        () -> assertFalse(employees.isEmpty()),
                        () -> assertEquals(4, employees.size()));
            }, () -> {
                List<Team> teams = properties.teams();
                assertAll(
                        () -> assertFalse(teams.isEmpty()),
                        () -> assertEquals(1, teams.size()),
                        () -> {
                            Team team = teams.get(0);
                            assertAll(
                                    () -> assertEquals(3, team.involvedEmployees().size()),
                                    () -> assertEquals(List.of(ILIA, PETER, JIM), team.involvedEmployees())
                            );
                        });
            });

        }
    }

}
