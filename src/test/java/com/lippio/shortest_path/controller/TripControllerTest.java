package com.lippio.shortest_path.controller;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lippio.shortest_path.DataUtils;
import com.lippio.shortest_path.ShortestPathApplication;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.service.TripRequestService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ShortestPathApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripRequestService tripRequestService;

    @MockBean
    private TripRequestValidatorService tripRequestValidatorService;

    @Test
    void shouldReturnAllTrips() throws Exception {
        // given
        List<ResponseTripDTO> responseTripDTOList = new ArrayList<>();

        ResponseTripDTO responseTripDTO1 = DataUtils.mockResponseTripDTO(Map.of());
        ResponseTripDTO responseTripDTO2 = DataUtils.mockResponseTripDTO(
            Map.of("name", "Trip 2",
                "countries", "\"IRN\",\"PAK\",\"IND\"",
                "createdAt", LocalDateTime.now()));
        responseTripDTOList.add(responseTripDTO1);
        responseTripDTOList.add(responseTripDTO2);

        Mockito.when(tripRequestService.getAllTrips())
            .thenReturn(responseTripDTOList);

        // when
        MvcResult result = mockMvc.perform(get("/trip")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        verify(tripRequestService, times(1)).getAllTrips();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<ResponseTripDTO> countryDetailsDTOs = Arrays.asList(objectMapper
            .readValue(result.getResponse().getContentAsString(), ResponseTripDTO[].class));

        assertIterableEquals(responseTripDTOList, countryDetailsDTOs);
    }

    @Test
    void shouldRequestSaveTrip() throws Exception {
        // given
        RequestTripDTO requestTripDTO = DataUtils.mockRequestTripDTO(Map.of());

        ObjectMapper objectMapper = new ObjectMapper();
        final String requestBody = objectMapper.writeValueAsString(requestTripDTO);

        // when
        mockMvc.perform(post("/trip")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated())
            .andReturn();

        verify(tripRequestService, times(1)).saveTrip(requestTripDTO);
    }
    @Test
    void shouldThrowWrongFormatExceptionTrip() throws Exception {
        // given
        RequestTripDTO requestTripDTO = DataUtils.mockRequestTripDTO(Map.of("countries", "\"NAMA\",\"VNM\""));

        ObjectMapper objectMapper = new ObjectMapper();
        final String requestBody = objectMapper.writeValueAsString(requestTripDTO);

        Mockito.doThrow(new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT))
            .when(tripRequestValidatorService).validate(Mockito.any());

        // when
        MvcResult result = mockMvc.perform(post("/trip")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(tripRequestService, times(0)).saveTrip(any());
        assertThat(result.getResolvedException().getMessage()).isEqualTo(Errors.INCORRECT_COUNTRY_LIST_FORMAT.getMessage());
    }
}