package com.lippio.shortest_path;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public final class DataUtils {

    private DataUtils(){};

    public static ResponseTripDTO mockResponseTripDTO(final Map<String, Object> mockedData) {
        return ResponseTripDTO.builder()
            .name((String) Optional.ofNullable(mockedData.get("name"))
                .orElse("Trip 1"))
            .countries((String) Optional.ofNullable(mockedData.get("countries"))
                .orElse("\"NAM\",\"VNM\""))
            .createdAt((LocalDateTime) Optional.ofNullable(mockedData.get("createdAt"))
                .orElse(LocalDateTime.of(2022, 5, 15, 12, 30)))
            .build();
    }

    public static RequestTripDTO mockRequestTripDTO(final Map<String, Object> mockedData) {
        return RequestTripDTO.builder()
            .name((String) Optional.ofNullable(mockedData.get("name"))
                .orElse("Trip 1"))
            .countries((String) Optional.ofNullable(mockedData.get("countries"))
                .orElse("\"NAM\",\"VNM\""))
            .build();
    }
}
