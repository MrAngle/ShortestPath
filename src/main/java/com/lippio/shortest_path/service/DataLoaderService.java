package com.lippio.shortest_path.service;

import com.lippio.shortest_path.pojo.Country;

import java.util.Set;

/**
 * The interface Data loader service.
 */
public interface DataLoaderService {

    /**
     * Gets countries.
     *
     * @return the countries
     */
    Set<Country> getCountries();

}
