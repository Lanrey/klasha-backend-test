package com.klasha.countries;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CountryControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getNumberOfCities() throws Exception {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/api/country/number-of-cities?numberOfCities=10", Object.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void getCountryInformation() throws Exception {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/api/country/get-country-information?country=Nigeria", Object.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void getFullListOfStatesAndCities() throws Exception {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/api/country/get-list-of-states-and-cities?country=Nigeria", Object.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getCountryCurrency() throws Exception {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/api/country/get-currency?country=Italy&amount=1000&targetCurrency=NGN", Object.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
