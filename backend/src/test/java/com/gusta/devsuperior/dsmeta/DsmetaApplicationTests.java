package com.gusta.devsuperior.dsmeta;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DsmetaApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context.getBean("filterChain", SecurityFilterChain.class)).isNotNull();

        assertThat(context.getBean("corsConfigurationSource", CorsConfigurationSource.class)).isNotNull();
    }

}

