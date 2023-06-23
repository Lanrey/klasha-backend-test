package com.klasha.countries;

import com.klasha.countries.controller.CountryController;
import com.klasha.countries.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
public class CountryControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CountryService countryService;

    @Test
    public void getNumberOfCities() throws Exception{
        mockMvc.perform(get("/api/country/number-of-cities?numberOfCities=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(countryService, times(1)).getCitiesPopulationInCountry(10);
    }

    @Test
    public void getCountryInformation() throws Exception{
        mockMvc.perform(get("/api/country/get-country-information?country=Nigeria"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(countryService, times(1)).getCountryDetails("Nigeria");
    }

    @Test
    public void getFullListOfStatesAndCities() throws Exception{
        mockMvc.perform(get("/api/country/get-list-of-states-and-cities?country=Nigeria"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(countryService, times(1)).getStatesAndCitiesOfCountry("Nigeria");
    }

    @Test
    public void getCountryCurrency() throws Exception{
        mockMvc.perform(get("/api/country/get-currency?country=Italy&amount=1000&targetCurrency=NGN"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(countryService, times(1)).getCountryCurrency("Italy","NGN",1000);
    }
}
