package com.lippio.shortest_path.service;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.dijkstra.DijkstraAlgorithm;
import com.lippio.shortest_path.pojo.Country;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DijkstraServiceImpl implements DijkstraService {

    DataLoaderService dataLoaderService;

    @Override
    public List<String> getShortestPath(String from, String to) {

        Set<Country> countries = dataLoaderService.loadData();

        Set<CountryNode> countryNodes = CountryNode.toCountryNodes(countries);
        CountryNode fromNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase(from)).findFirst().orElseThrow();
        CountryNode toNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase(to)).findFirst().orElseThrow();

        List<CountryNode> shortestPath = DijkstraAlgorithm.calculateShortestPathFromSource(fromNode, toNode);
        return shortestPath.stream().map(x -> x.getCountry().getCountryCode()).toList();
    }
}