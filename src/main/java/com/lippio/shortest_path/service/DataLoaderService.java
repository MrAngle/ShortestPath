package com.lippio.shortest_path.service;

import com.lippio.shortest_path.pojo.Country;

import java.util.Set;

public interface DataLoaderService {

    Set<Country> loadData();

}
