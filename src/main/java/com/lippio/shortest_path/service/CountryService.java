package com.lippio.shortest_path.service;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.pojo.Country;

import java.util.Set;
import java.util.function.Predicate;

/**
 * The interface Country service.
 */
public interface CountryService {

    /**
     * Gets countries.
     *
     * @return the countries
     */
    Set<Country> getCountries();

    /**
     * Gets country.
     *
     * @param countryCode       the country code
     * @param countryIdentifier the country identifier
     * @return the country
     */
    Country getCountry(String countryCode, CountryIdentifier countryIdentifier);

    /**
     * Gets all country nodes.
     *
     * @return the all country nodes
     */
    Set<CountryNode> getAllCountryNodes();

    /**
     * Gets country node.
     *
     * @param countryNodes the country nodes
     * @param filter       the filter
     * @return the country node
     */
    CountryNode getCountryNode(Set<CountryNode> countryNodes, Predicate<CountryNode> filter);

}
