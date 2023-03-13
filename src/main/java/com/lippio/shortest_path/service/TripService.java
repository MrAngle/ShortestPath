package com.lippio.shortest_path.service;

import com.lippio.shortest_path.entity.TripEntity;

import java.util.List;

public interface TripService {
    void saveTrip(final TripEntity tripDTO);
    List<TripEntity> getAllTrips();
}
