package com.lippio.shortest_path.service;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;

import java.util.List;

/**
 * The interface Trip request service. Its used as additional class between Trip service and controller.
 */
public interface TripRequestService {

    /**
     * Prepare/map dto to application related object and save trip.
     *
     * @param tripDTO the trip dto
     */
    void saveTrip(final RequestTripDTO tripDTO);


    /**
     * Gets all trips and map to DTOs as return type
     *
     * @return the all trips
     */
    List<ResponseTripDTO> getAllTrips();
}
