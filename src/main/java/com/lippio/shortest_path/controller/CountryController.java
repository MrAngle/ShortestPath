package com.lippio.shortest_path.controller;

import com.example.api.generated.model.CountryDetailsDTO;
import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.example.generated.api.CountryApi;
import com.lippio.shortest_path.mapper.controllers_dto.CountryRoutingMapper;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.CountryService;
import com.lippio.shortest_path.service.ShortestPathService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class CountryController implements CountryApi {

    private final ShortestPathService shortestPathService;
    private final CountryService countryService;

    @Override
    public ResponseEntity<CountryDetailsDTO> countryDetails(final Map<String, String> headers,
                                                            final String countryCode,
                                                            final CountryIdentifierTypeDTO countryIdentifierType) {
        log.info("Received GET country details with countryCode: {}, countryIdentifierType: {}",
            countryCode,
            countryIdentifierType);
        final Country country = countryService.getCountry(
            countryCode,
            CountryRoutingMapper.INSTANCE.toCountryIdentifierType(countryIdentifierType));
        return ResponseEntity.status(HttpStatus.OK).body(CountryRoutingMapper.INSTANCE.toCountryDetailsDTO(country));
    }

    @Override
    public ResponseEntity<List<String>> countryRouting(final Map<String, String> headers,
                                                       final String origin,
                                                       final String destination,
                                                       final CountryIdentifierTypeDTO countryIdentifierType) {
        log.info("Received GET calculate path with origin: {}, destination: {}, countryIdentifierType: {}",
            origin,
            destination,
            countryIdentifierType);
        final List<String> shortestPath = shortestPathService.getShortestPath(
            origin,
            destination,
            countryIdentifierType);
        return ResponseEntity.status(HttpStatus.OK).body(shortestPath);
    }
}