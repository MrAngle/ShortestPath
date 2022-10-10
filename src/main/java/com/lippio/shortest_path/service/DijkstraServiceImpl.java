package com.lippio.shortest_path.service;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.dijkstra.DijkstraAlgorithm;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DijkstraServiceImpl implements DijkstraService {

    private DataLoaderService dataLoaderService;

    @Override
    public List<String> getShortestPath(String from, String to) {
        Set<CountryNode> countryNodes = CountryNode.toCountryNodes(dataLoaderService.getCountries());
        CountryNode fromNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase(from)).findFirst().orElseThrow(() -> new RestException(Errors.COUNTRY_NOT_FOUND));
        CountryNode toNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase(to)).findFirst().orElseThrow(() -> new RestException(Errors.COUNTRY_NOT_FOUND));

        List<CountryNode> shortestPath = DijkstraAlgorithm.calculateShortestPathFromSource(fromNode, toNode);
        if(shortestPath == null || shortestPath.isEmpty()) {
            throw new RestException(Errors.PATH_NOT_FOUND);
        }
        return shortestPath.stream().map(x -> x.getCountry().getCountryCode()).toList();
    }
}