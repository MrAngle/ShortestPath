package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryRoutingMapper {
    CountryRoutingMapper INSTANCE = Mappers.getMapper(CountryRoutingMapper.class);

    CountryIdentifier toCountryIdentifierType(CountryIdentifierTypeDTO candidateStatusInfo);

}
