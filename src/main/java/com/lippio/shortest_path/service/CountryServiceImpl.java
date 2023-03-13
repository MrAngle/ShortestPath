package com.lippio.shortest_path.service;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Predicate;

@Service
public class CountryServiceImpl implements CountryService {
    private final DataLoaderService dataLoaderService;

    CountryServiceImpl(final DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @Override
    public Set<Country> getCountries() {
        return dataLoaderService.getCountries();
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
}
