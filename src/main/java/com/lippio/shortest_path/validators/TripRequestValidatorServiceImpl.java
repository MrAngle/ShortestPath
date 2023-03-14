package com.lippio.shortest_path.validators;

import com.example.api.generated.model.RequestTripDTO;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TripRequestValidatorServiceImpl implements TripRequestValidatorService {
    @Override
    public void validate(final RequestTripDTO requestTripDTO) {
        validateCountryList(requestTripDTO.getCountries());
    }

    @Override
    public void validateCountryList(final String countryList) {
        if(!isValidCountryList(countryList)) {
            log.error("Incorrect country list format");
            throw new RestException(Errors.INCORRECT_COUNTRY_LIST_FORMAT);
        }
    }

    private static boolean isValidCountryList(String value) {
        if (value == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[A-Z]{3}(,[A-Z]{3})*$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
