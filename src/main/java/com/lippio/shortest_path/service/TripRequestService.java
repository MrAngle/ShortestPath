package com.lippio.shortest_path.service;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;

import java.util.List;

public interface TripRequestService {
    void saveTrip(final RequestTripDTO tripDTO);
    List<ResponseTripDTO> getAllTrips();
}
