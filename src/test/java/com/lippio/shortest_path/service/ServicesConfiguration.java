package com.lippio.shortest_path.service;

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

}
