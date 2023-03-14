package com.lippio.shortest_path.controller;

import com.example.api.generated.model.CountryDetailsDTO;
import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lippio.shortest_path.ShortestPathApplication;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.CountryService;
import com.lippio.shortest_path.service.ShortestPathService;
import com.lippio.shortest_path.validators.TripRequestValidatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ShortestPathApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortestPathService shortestPathService;

    @MockBean
    private CountryService countryService;

    @MockBean
    private TripRequestValidatorService tripRequestValidatorService;

    @Test
    void shouldReturnCountryDetails() throws Exception {
        // given
        final Country country = Country.builder()
            .name("Poland")
            .countryCode("POL")
            .region("Europe")
            .isoCode("PL")
            .coordinates(new float[]{52.237049f, 21.017532f})
            .build();

        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.CCA_3;
        CountryIdentifier countryIdentifier= CountryIdentifier.CCA_3;

        Mockito.when(countryService.getCountry("POL", countryIdentifier))
            .thenReturn(country);

        // when
        MvcResult result = mockMvc.perform(get("/country/POL")
                .param("countryIdentifierType", countryIdentifierTypeDTO.getValue())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        CountryDetailsDTO countryDetailsDTO =
            new ObjectMapper().readValue(result.getResponse().getContentAsString(), CountryDetailsDTO.class);

        assertAll(
            () -> assertEquals(countryDetailsDTO.getName(), country.getName()),
            () -> assertEquals(countryDetailsDTO.getIsoCode(), country.getIsoCode()),
            () -> assertEquals(countryDetailsDTO.getRegion(), country.getRegion()),
            () -> assertEquals(countryDetailsDTO.getCountryCode(), country.getCountryCode()));
    }

    @Test
    void shouldThrowCountryNotFound() throws Exception {
        // given
        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.CCA_3;
        CountryIdentifier countryIdentifier= CountryIdentifier.CCA_3;

        Mockito.when(countryService.getCountry(any(), eq(countryIdentifier)))
            .thenThrow(new RestException(Errors.COUNTRY_NOT_FOUND));

        // when
        MvcResult result = mockMvc.perform(get("/country/WRONG")
                .param("countryIdentifierType", countryIdentifierTypeDTO.getValue())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

        // then
        verify(countryService, times(1))
            .getCountry("WRONG", countryIdentifier);

        assertThat(result.getResolvedException().getMessage()).isEqualTo(Errors.COUNTRY_NOT_FOUND.getMessage());
    }

    @Test
    void shouldReturnShortestPath() throws Exception {
        // given
        List<String> shortestPath = Arrays.asList("PL", "DE", "FR");

        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.ISO_CODE;
        Mockito.when(shortestPathService.getShortestPath(any(), any(), eq(countryIdentifierTypeDTO)))
            .thenReturn(shortestPath);

        // when
        MvcResult result = mockMvc.perform(get("/country/routing")
                .param("countryIdentifierType", "ISO_CODE")
                .param("from", "PL")
                .param("to", "FR")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        // then
        verify(shortestPathService, times(1))
            .getShortestPath("PL", "FR", countryIdentifierTypeDTO);
        assertThat(result.getResponse().getContentAsString())
            .isEqualTo("[\"PL\",\"DE\",\"FR\"]");
    }

    @Test
    void shouldThrowPathNotFound() throws Exception {
        // given
        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.CCA_3;

        Mockito.when(shortestPathService.getShortestPath(any(), any(), eq(countryIdentifierTypeDTO)))
            .thenThrow(new RestException(Errors.PATH_NOT_FOUND));

        // when
        MvcResult result = mockMvc.perform(get("/country/routing")
                .param("countryIdentifierType", countryIdentifierTypeDTO.getValue())
                .param("from", "PL")
                .param("to", "FR")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(shortestPathService, times(1))
            .getShortestPath("PL", "FR", countryIdentifierTypeDTO);

        assertThat(result.getResolvedException().getMessage()).isEqualTo(Errors.PATH_NOT_FOUND.getMessage());
    }
}