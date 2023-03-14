package com.lippio.shortest_path.controller;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.example.generated.api.TripApi;
import com.lippio.shortest_path.service.TripRequestService;
import com.lippio.shortest_path.validators.TripRequestValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TripController implements TripApi {
    private final TripRequestService tripRequestService;

    private final TripRequestValidatorService tripRequestValidatorService;

    @Override
    public ResponseEntity<List<ResponseTripDTO>> getAll(final Map<String, String> headers) {
        // TODO: add pagination
        final List<ResponseTripDTO> tripDTOS = tripRequestService.getAllTrips();
        return ResponseEntity.status(HttpStatus.OK).body(tripDTOS);
    }

    @Override
    public ResponseEntity<Void> saveTrip(final Map<String, String> headers, final RequestTripDTO tripDTO) {
        tripRequestValidatorService.validate(tripDTO);
        tripRequestService.saveTrip(tripDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
