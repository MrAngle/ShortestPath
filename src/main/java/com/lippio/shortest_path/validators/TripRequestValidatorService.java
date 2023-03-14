package com.lippio.shortest_path.validators;

import com.example.api.generated.model.RequestTripDTO;

/**
 * The interface Trip request validator service.
 */
public interface TripRequestValidatorService {

    /**
     * Validate RequestTripDTO.
     *
     * @param requestTripDTO the request trip dto
     * @throws com.lippio.shortest_path.errors.RestException if wrong format
     */
    void validate(RequestTripDTO requestTripDTO);

    /**
     * Validate country list.
     *
     * @param countryList the country list
     * @throws com.lippio.shortest_path.errors.RestException if wrong format
     */
    void validateCountryList(String countryList);

}
