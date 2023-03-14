package com.lippio.shortest_path.service.impl;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.lippio.shortest_path.entity.TripEntity;
import com.lippio.shortest_path.mapper.controllers_dto.TripMapper;
import com.lippio.shortest_path.service.TripRequestService;
import com.lippio.shortest_path.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TripRequestServiceImpl implements TripRequestService {

    private final TripService tripService;

    @Override
    public void saveTrip(final RequestTripDTO tripDTO) {
        final TripEntity tripEntity = TripMapper.INSTANCE.toTripEntity(tripDTO, LocalDateTime.now());
        tripService.saveTrip(tripEntity);
    }

    @Override
    public List<ResponseTripDTO> getAllTrips() {
        final List<TripEntity> tripEntities = tripService.getAllTrips();
        return tripEntities.stream().map(TripMapper.INSTANCE::toResponseTripDTO).toList();
    }
}
