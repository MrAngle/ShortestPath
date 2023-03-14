package com.lippio.shortest_path.service;

import com.lippio.shortest_path.entity.TripEntity;

import java.util.List;

/**
 * The interface Trip service.
 */
public interface TripService {


    /**
     * Save trip.
     *
     * @param tripDTO the trip dto
     */
    void saveTrip(final TripEntity tripDTO);

    /**
     * Gets all trips.
     *
     * @return the all trips
     */
    List<TripEntity> getAllTrips();
}
