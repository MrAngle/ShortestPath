package com.lippio.shortest_path.service;

import com.lippio.shortest_path.pojo.Country;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile(value = "prod")
public class DataLoaderByLinkServiceImpl implements DataLoaderService {

    RestTemplate restTemplate;

    @Override
    public Set<Country> loadData() {
        Country[] response = restTemplate.getForObject(
                "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", Country[].class);

        HashSet<Country> countries = new HashSet<>(Arrays.asList(response));
        Country.connectAllCountries(countries);
        return countries;
    }


}