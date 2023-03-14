package com.lippio.shortest_path.service;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;

import java.util.List;

/**
 * The interface Shortest path service.
 */
public interface ShortestPathService {

    /**
     * Calculate shortest path.
     *
     * @param origin            the origin
     * @param destination       the destination
     * @param countryIdentifier the country identifier
     * @return the shortest path
     */
    List<String> getShortestPath(String origin, String destination, CountryIdentifier countryIdentifier);

    /**
     * Calculate shortest path.
     *
     * @param origin            the origin
     * @param destination       the destination
     * @param countryIdentifier the country identifier
     * @return the shortest path
     */
    List<String> getShortestPath(String origin, String destination, CountryIdentifierTypeDTO countryIdentifier);

}
