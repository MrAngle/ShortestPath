package com.lippio.shortest_path.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
//@AllArgsConstructor
@Profile({ "dev", "test", "default"})
@Slf4j
public class DataLoaderByFileServiceImpl implements DataLoaderService {

    private final Set<Country> countries = new HashSet<>();

    public DataLoaderByFileServiceImpl() {
        countries.addAll(DataLoaderByFileServiceImpl.loadData());
    }

    @Override
    public Set<Country> getCountries() {
        return countries;
    }


    private static Set<Country> loadData() {
        ObjectMapper mapper = new ObjectMapper();

        Country[] map;
        try {
            map = mapper.readValue(ResourceUtils.getFile("classpath:countries.json"), Country[].class);
        } catch (IOException e) {
            log.error("Unable to read value: {0}", e.getCause());
            throw new RestException(Errors.FETCHING_COUNTRY_INTERNAL_ERROR);
        }
        HashSet<Country> countries = new HashSet<>(Arrays.asList(map));
        Country.connectAllCountries(countries);

        return countries;
    }
}