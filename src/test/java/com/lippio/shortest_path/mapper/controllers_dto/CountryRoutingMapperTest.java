package com.lippio.shortest_path.mapper.controllers_dto;

import com.example.api.generated.model.CountryDetailsDTO;
import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.pojo.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryRoutingMapperTest {

    private final CountryRoutingMapper mapper = CountryRoutingMapper.INSTANCE;
    @Test
    void toCountryIdentifierType() {
        CountryIdentifierTypeDTO countryIdentifierTypeDTO = CountryIdentifierTypeDTO.ISO_CODE;
        CountryIdentifier countryIdentifier = mapper.toCountryIdentifierType(countryIdentifierTypeDTO);

        assertEquals("ISO_CODE", countryIdentifier.getValue());
    }

    @Test
    void toCountryDetailsDTO() {
        Country country = Country.builder()
            .name("testName")
            .countryCode("POL")
            .isoCode("PL")
            .region("region")
            .coordinates(new float[]{2, 3})
            .build();
        CountryDetailsDTO countryIdentifier = mapper.toCountryDetailsDTO(country);

        assertAll(
            () -> assertEquals(country.getName(), countryIdentifier.getName()),
            () -> assertEquals(country.getCountryCode(), countryIdentifier.getCountryCode()),
            () -> assertEquals(country.getIsoCode(), countryIdentifier.getIsoCode()),
            () -> assertEquals(country.getCoordinates()[0], countryIdentifier.getCoordinates().get(0)),
            () -> assertEquals(country.getCoordinates()[1], countryIdentifier.getCoordinates().get(1)),
            () -> assertEquals(country.getRegion(), countryIdentifier.getRegion()));
    }
}