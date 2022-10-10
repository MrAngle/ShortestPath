package com.lippio.shortest_path.service;

import com.lippio.shortest_path.pojo.Country;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "prod")
public class DataLoaderByLinkServiceImpl implements DataLoaderService {

    private final Set<Country> countries = new HashSet<>();

    private String dataSource;
    private RestTemplate restTemplate;

    public DataLoaderByLinkServiceImpl(@Value("${data.source}") String dataSource, RestTemplate restTemplate) {
        this.dataSource = dataSource;
        this.restTemplate = restTemplate;
    }

    @Override
    public Set<Country> getCountries() {
        if(countries.isEmpty()) {
            countries.addAll(this.loadData());
        }
        return countries;
    }

    private Set<Country> loadData() {
        Country[] response = restTemplate.getForObject(dataSource, Country[].class);
        HashSet<Country> countries = new HashSet<>(Arrays.asList(response));
        Country.connectAllCountries(countries);
        return countries;
    }


}