package com.lippio.shortest_path.service;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.pojo.Country;

import java.util.Set;
import java.util.function.Predicate;

public interface CountryService {

    Set<Country> getCountries();

    Set<CountryNode> getAllCountryNodes();
    CountryNode getCountryNode(Set<CountryNode> countryNodes, Predicate<CountryNode> filter);

}
