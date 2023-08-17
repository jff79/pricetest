package com.itx.pricetest.infrastructure.in.controller;

import com.itx.pricetest.infrastructure.in.api.model.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void givenBrandIdProductIdAndDate20200614100000_whenPricesSearch_thenStatus200() throws Exception {
        this.executeTestPricesSearch("2020-06-14T10:00:00.000Z", 35.5);
    }

    @Test
    void givenBrandIdProductIdAndDate20200614160000_whenPricesSearch_thenStatus200() throws Exception {
        this.executeTestPricesSearch("2020-06-14T16:00:00.000Z", 25.45);
    }

    @Test
    void givenBrandIdProductIdAndDate20200614210000_whenPricesSearch_thenStatus200() throws Exception {
        this.executeTestPricesSearch("2020-06-14T21:00:00.000Z", 35.5);
    }

    @Test
    void givenBrandIdProductIdAndDate20200615100000_whenPricesSearch_thenStatus200() throws Exception {
        this.executeTestPricesSearch("2020-06-15T10:00:00.000Z", 30.5);
    }

    @Test
    void givenBrandIdProductIdAndDate20200616210000_whenPricesSearch_thenStatus200() throws Exception {
        this.executeTestPricesSearch("2020-06-16T21:00:00.000Z", 38.95);
    }

    @Test
    void givenBrandIdProductId_whenPricesSearch_thenStatus400() throws Exception {
        this.webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/prices/search")
                                .queryParam("brandId", "1")
                                .queryParam("productId", "35455")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    private void executeTestPricesSearch(String applyDate, Double expectedPrice) throws Exception {
        this.webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/prices/search")
                                .queryParam("brandId", "1")
                                .queryParam("productId", "35455")
                                .queryParam("applyDate", applyDate)
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponse.class)
                .value(response ->
                {
                    assertEquals(35455L, response.getProductId());
                    assertEquals(expectedPrice, response.getPrice());
                });
    }

}