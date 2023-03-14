package com.lippio.shortest_path.validators;

import com.example.api.generated.model.RequestTripDTO;

public interface TripValidatorService {

    void basicValidation(RequestTripDTO requestTripDTO);

}
