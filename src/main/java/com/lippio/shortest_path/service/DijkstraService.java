package com.lippio.shortest_path.service;

import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.pojo.RequestShortestPathData;

import java.util.List;

public interface DijkstraService {
    List<String> getShortestPath(String origin, String destination, CountryIdentifier countryIdentifier);
    List<String> getShortestPath(Country origin, Country destination);
    List<String> getShortestPath(RequestShortestPathData requestShortestPathData);

}
