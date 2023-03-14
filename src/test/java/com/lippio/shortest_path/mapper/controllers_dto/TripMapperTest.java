package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.lippio.shortest_path.entity.TripEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripMapperTest {

    @Test
    void shouldMapToTripEntity() {
        // given
        RequestTripDTO requestTripDTO = new RequestTripDTO();
        requestTripDTO.setName("Test trip");
        requestTripDTO.setCountries("Poland, Germany");

        LocalDateTime now = LocalDateTime.now();

        // when
        TripEntity tripEntity = TripMapper.INSTANCE.toTripEntity(requestTripDTO, now);

        // then
        assertEquals(requestTripDTO.getName(), tripEntity.getName());
        assertEquals(requestTripDTO.getCountries(), tripEntity.getCountries());
        assertEquals(now, tripEntity.getCreatedAt());
    }

    @Test
    void shouldMapToResponseTripDTO() {
        // given
        UUID tripId = UUID.randomUUID();
        String tripName = "Test trip";
        String tripCountries = "Poland, Germany";
        LocalDateTime createdAt = LocalDateTime.now();

        TripEntity tripEntity = TripEntity.builder()
            .id(tripId)
            .name(tripName)
            .countries(tripCountries)
            .createdAt(createdAt)
            .build();

        // when
        ResponseTripDTO responseTripDTO = TripMapper.INSTANCE.toResponseTripDTO(tripEntity);

        // then
        assertEquals(tripName, responseTripDTO.getName());
        assertEquals(tripCountries, responseTripDTO.getCountries());
        assertEquals(createdAt, responseTripDTO.getCreatedAt());
    }

}