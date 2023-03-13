package com.lippio.shortest_path.service;

import com.lippio.shortest_path.entity.TripEntity;
import com.lippio.shortest_path.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    public void saveTrip(final TripEntity tripEntity) {
        tripRepository.save(tripEntity);
    }

    @Override
    public List<TripEntity> getAllTrips() {
        return tripRepository.findAll();
    }
}
