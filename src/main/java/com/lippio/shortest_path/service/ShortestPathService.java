package com.lippio.shortest_path.service;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;

import java.util.List;

public interface ShortestPathService {

    List<String> getShortestPath(String origin, String destination, CountryIdentifier countryIdentifier);
    List<String> getShortestPath(String origin, String destination, CountryIdentifierTypeDTO countryIdentifier);

}
