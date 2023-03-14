package com.lippio.shortest_path.service.impl;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.dijkstra.DijkstraAlgorithm;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.pojo.RequestShortestPathData;
import com.lippio.shortest_path.service.CountryService;
import com.lippio.shortest_path.service.DijkstraService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DijkstraServiceImpl implements DijkstraService {

    private final CountryService countryService;

    @Override
    public List<String> getShortestPath(final Country origin, final Country destination) {
        return getShortestPath(origin.getCountryCode(),
            destination.getCountryCode(),
            CountryIdentifier.CCA_3);
    }

    @Override
    public List<String> getShortestPath(final RequestShortestPathData requestShortestPathData) {
        return getShortestPath(requestShortestPathData.origin(),
            requestShortestPathData.destination(),
            requestShortestPathData.countryIdentifier());
    }

    @Override
    public List<String> getShortestPath(final String origin,
                                        final String destination,
                                        final CountryIdentifier countryIdentifier) {
        final Set<CountryNode> countryNodes = countryService.getAllCountryNodes();

        final CountryNode originNode = countryService.getCountryNode(countryNodes,
            countryIdentifier.getCountryNodeFilterPredicate(origin));
        final CountryNode destinationNode = countryService.getCountryNode(countryNodes,
            countryIdentifier.getCountryNodeFilterPredicate(destination));

        return calculate(originNode, destinationNode);
    }

    private static List<String> calculate(final CountryNode origin, final CountryNode destination) {
        final List<CountryNode> shortestPath = DijkstraAlgorithm.calculateShortestPathFromSource(origin, destination);
        if (shortestPath == null || shortestPath.isEmpty()) {
            throw new RestException(Errors.PATH_NOT_FOUND);
        }
        return shortestPath.stream().map(x -> x.getCountry().getCountryCode()).toList();
    }
}