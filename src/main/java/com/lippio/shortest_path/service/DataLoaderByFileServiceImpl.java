package com.lippio.shortest_path.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lippio.shortest_path.pojo.Country;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile({ "dev", "test", "default"})
public class DataLoaderByFileServiceImpl implements DataLoaderService {

    private final Set<Country> countries = new HashSet<>();

    @Override
    public Set<Country> getCountries() {
        if(countries.isEmpty()) {
            countries.addAll(this.loadData());
        }
        return countries;
    }


    private Set<Country> loadData() {
        ObjectMapper mapper = new ObjectMapper();

        Country[] map = null;
        try {
            map = mapper.readValue(ResourceUtils.getFile("classpath:countries.json"), Country[].class);
        } catch (IOException e) {
            // TODO: Handle it
            e.printStackTrace();
        }
        HashSet<Country> countries = new HashSet<>(Arrays.asList(map));
        Country.connectAllCountries(countries);

        return countries;
    }
}