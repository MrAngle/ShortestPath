package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.RequestTripDTO;
import com.example.api.generated.model.ResponseTripDTO;
import com.lippio.shortest_path.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mapping(source = "creationDateParam", target = "createdAt")
    TripEntity toTripEntity(RequestTripDTO tripDTO, LocalDateTime creationDateParam);
    ResponseTripDTO toResponseTripDTO(TripEntity tripEntity);

}
