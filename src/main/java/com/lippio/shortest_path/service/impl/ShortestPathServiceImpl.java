package com.lippio.shortest_path.service.impl;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.mapper.controllers_dto.CountryRoutingMapper;
import com.lippio.shortest_path.pojo.RequestShortestPathData;
import com.lippio.shortest_path.service.DijkstraService;
import com.lippio.shortest_path.service.ShortestPathService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ShortestPathServiceImpl implements ShortestPathService {

    private final DijkstraService dijkstraService;

    @Override
    public List<String> getShortestPath(final String origin,
                                        final String destination,
                                        final CountryIdentifierTypeDTO countryIdentifier) {
        return getShortestPath(
            origin,
            destination,
            CountryRoutingMapper.INSTANCE.toCountryIdentifierType(countryIdentifier));
    }

    @Override
    public List<String> getShortestPath(final String origin,
                                        final String destination,
                                        final CountryIdentifier countryIdentifier) {
        final RequestShortestPathData requestShortestPathData = prepareRequest(origin, destination, countryIdentifier);
        log.info("Calculate shortest path for: {}", requestShortestPathData);
        return dijkstraService.getShortestPath(requestShortestPathData);
    }

    private static RequestShortestPathData prepareRequest(final String origin,
                                           final String destination,
                                           final CountryIdentifier countryIdentifier) {
        return RequestShortestPathData.builder()
            .origin(origin)
            .destination(destination)
            .countryIdentifier(countryIdentifier)
            .build();
    }

}
