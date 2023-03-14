package com.lippio.shortest_path.validators;

import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.service.ServicesConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TripRequestValidatorServiceImplTest {

    final TripRequestValidatorService validatorService = ServicesConfiguration.tripRequestValidatorService();

    @ParameterizedTest
    @MethodSource("provideData")
    void shouldValidateCountryList(String countryList, RuntimeException expectedException) {
        try {
            validatorService.validateCountryList(countryList);
            assertNull(expectedException);
        } catch (RestException exception) {
            assertNotNull(expectedException);
            assertEquals(exception.getErrors().getMessage(), expectedException.getMessage());
        }
    }

    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of("IRN,PAK,IND", null),
            Arguments.of("IRN,pak", new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT)),
            Arguments.of("IRN,PAK,IND,RUS,POL,GER", null),
            Arguments.of("IRN,,PAK", new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT)),
            Arguments.of(",,", new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT)),
            Arguments.of("IRN", null),
            Arguments.of("", new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT)),
            Arguments.of("IRN,PAK,IND,RUSS", new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT))
        );
    }
}