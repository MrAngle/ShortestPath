package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.lippio.shortest_path.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    TripEntity toTripEntity(RequestTripDTO tripDTO);
    ResponseTripDTO toResponseTripDTO(TripEntity tripEntity);
}
