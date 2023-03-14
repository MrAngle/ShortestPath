package com.lippio.shortest_path.service.impl;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.CountryService;
import com.lippio.shortest_path.service.DataLoaderService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Predicate;


@Service
public class CountryServiceImpl implements CountryService {
    private final DataLoaderService dataLoaderService;

    public CountryServiceImpl(final DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @Override
    public Set<Country> getCountries() {
        return dataLoaderService.getCountries();
    }

    @Override
    public Country getCountry(String countryCode, CountryIdentifier countryIdentifier) {
        return findCountry(countryIdentifier.getCountryFilterPredicate(countryCode));
    }

    @Override
    public Set<CountryNode> getAllCountryNodes() {
        return CountryNode.toCountryNodes(getCountries());
    }

    @Override
    public CountryNode getCountryNode(Set<CountryNode> countryNodes, final Predicate<CountryNode> filter) {
        return countryNodes.stream()
            .filter(filter)
            .findFirst()
            .orElseThrow(() -> new RestException(Errors.COUNTRY_NOT_FOUND));
    }

    private Country findCountry(final Predicate<Country> filter) {
        return getCountries().stream()
            .filter(filter)
            .findFirst()
            .orElseThrow(() -> new RestException(Errors.COUNTRY_NOT_FOUND));
    }
}
