package com.lippio.shortest_path.service;

import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.pojo.RequestShortestPathData;

import java.util.List;

/**
 * The interface Dijkstra service.
 */
public interface DijkstraService {
    /**
     * Calculate shortest route using dijkstra's algorithm
     *
     * @param origin            the origin
     * @param destination       the destination
     * @param countryIdentifier the country identifier
     * @return the shortest path
     */
    List<String> getShortestPath(String origin, String destination, CountryIdentifier countryIdentifier);

    /**
     * Calculate shortest route using dijkstra's algorithm
     *
     * @param origin      the origin
     * @param destination the destination
     * @return the shortest path
     */
    List<String> getShortestPath(Country origin, Country destination);

    /**
     * Calculate shortest route using dijkstra's algorithm
     *
     * @param requestShortestPathData the request shortest path data
     * @return the shortest path
     */
    List<String> getShortestPath(RequestShortestPathData requestShortestPathData);

}
