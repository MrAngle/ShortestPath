package com.lippio.shortest_path.service;

import com.example.api.generated.model.CountryIdentifierTypeDTO;
import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.mapper.controllers_dto.CountryRoutingMapper;
import com.lippio.shortest_path.pojo.RequestShortestPathData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
