package com.lippio.shortest_path.service.impl;

import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.DataLoaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "prod")
public class DataLoaderByLinkServiceImpl implements DataLoaderService {

    private final Set<Country> countries;
    private final String dataSource;
    private final RestTemplate restTemplate;

    public DataLoaderByLinkServiceImpl(@Value("${data.source}") String dataSource, final RestTemplate restTemplate) {
        this.dataSource = dataSource;
        this.restTemplate = restTemplate;
        countries = Collections.unmodifiableSet(this.loadData());
    }

    @Override
    public Set<Country> getCountries() {
        return new HashSet<>(countries);
    }

    private Set<Country> loadData() {
        Country[] response = restTemplate.getForObject(dataSource, Country[].class);
        if (response == null || response.length == 0) {
            throw new RestException(Errors.FETCHING_COUNTRY_INTERNAL_ERROR);
        }
        HashSet<Country> loadedCountries = new HashSet<>(Arrays.asList(response));
        Country.connectAllCountries(loadedCountries);
        return loadedCountries;
    }
}