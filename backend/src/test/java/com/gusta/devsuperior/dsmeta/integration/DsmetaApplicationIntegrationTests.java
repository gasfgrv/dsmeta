package com.gusta.devsuperior.dsmeta.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DsmetaApplicationIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void findSales() throws Exception {
        mockMvc.perform(get("/sales")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .queryParam("minDate", "2021-06-27")
                        .queryParam("maxDate", "2022-06-16"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void dontFindSales() throws Exception {
        mockMvc.perform(get("/sales")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .queryParam("minDate", "2023-02-28")
                        .queryParam("maxDate", "2024-02-28"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    void notifySms() throws Exception {
        mockMvc.perform(get("/sales/{id}/notification", 37)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

