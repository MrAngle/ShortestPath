package com.lippio.shortest_path.pojo;

import com.lippio.shortest_path.enums.CountryIdentifier;
import lombok.Builder;
@Builder
public record RequestShortestPathData(String origin, String destination, CountryIdentifier countryIdentifier) {
    // add more parameters if needed
}
