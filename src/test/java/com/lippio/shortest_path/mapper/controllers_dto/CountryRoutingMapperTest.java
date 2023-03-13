package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryRoutingMapperTest {

    private final CountryRoutingMapper mapper = CountryRoutingMapper.INSTANCE;
    @Test
    void mapperTest_CountryRoutingMapper() {
        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.ISO_CODE;
        CountryIdentifier countryIdentifier = mapper.toCountryIdentifierType(countryIdentifierTypeDTO);

        assertEquals("ISO_CODE", countryIdentifier.getValue());
    }
}