package com.lippio.shortest_path.service;

import com.lippio.shortest_path.service.impl.CountryServiceImpl;
import com.lippio.shortest_path.service.impl.DataLoaderByFileServiceImpl;
import com.lippio.shortest_path.service.impl.DijkstraServiceImpl;
import com.lippio.shortest_path.validators.TripRequestValidatorService;
import com.lippio.shortest_path.validators.TripRequestValidatorServiceImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ServicesConfiguration {

    public DataLoaderService dataLoaderService() {
        return new DataLoaderByFileServiceImpl();
    }

    public DijkstraService dijkstraService() {
        return new DijkstraServiceImpl(countryService());
    }

    public CountryService countryService() {
        return new CountryServiceImpl(dataLoaderService());
    }
    public TripRequestValidatorService tripRequestValidatorService() {
        return new TripRequestValidatorServiceImpl();
    }

}
