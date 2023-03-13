package com.lippio.shortest_path.controller;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.example.generated.api.CountryRoutingApi;
import com.lippio.shortest_path.service.ShortestPathService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
// TODO: Handle cross origin better
@CrossOrigin(origins = {"http://localhost:4200"})
public class CountryRoutingController implements CountryRoutingApi {

    private final ShortestPathService shortestPathService;

    @Override
    public ResponseEntity<List<String>> countryRouting(final Map<String, String> headers,
                                                       final String origin,
                                                       final String destination,
                                                       final CountryIdentifierTypeDTO countryIdentifierType) {
        System.out.println("Im here");
        List<String> shortestPath = shortestPathService.getShortestPath(
            origin,
            destination,
            countryIdentifierType);
        return ResponseEntity.status(HttpStatus.OK).body(shortestPath);
    }
}