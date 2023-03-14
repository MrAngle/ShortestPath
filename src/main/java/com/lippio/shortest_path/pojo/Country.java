package com.lippio.shortest_path.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lippio.shortest_path.dijkstra.IDijkstraNode;
import com.lippio.shortest_path.jackson.CountryNameDeserializer;
import com.lippio.shortest_path.jackson.NodeRelationDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country implements IDijkstraNode<Country> {
    private static final int EARTH_RADIUS_KM = 6371; // Earth radius in kilometers

    @JsonDeserialize(using = CountryNameDeserializer.class, as=String.class)
    @JsonProperty("name")
    private String name;

    @JsonProperty("cca3")
    private String countryCode;

    @JsonProperty("region")
    private String region;

    @JsonProperty("cca2")
    private String isoCode;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = NodeRelationDeserializer.class, as=Map.class)
    private Map<String, Country> borders;

    @JsonProperty("latlng")
    private float[] coordinates;

    public static void connectAllCountries(Set<Country> countries) {
        for (Country country: countries) {
            if(country.getBorders() == null || country.getBorders().isEmpty()) {
                continue;
            }

            for (Map.Entry<String, Country> entry : country.getBorders().entrySet()) {
                entry.setValue(
                        countries.stream().filter(x ->
                        entry.getKey().equalsIgnoreCase(x.getCountryCode())).findFirst().orElseThrow());
            }
        }
    }

    @Override
    public float calculateDistance(Country country) {
        return calculateDistance(this.getCoordinates(), country.getCoordinates());
    }

    @Override
    public List<Country> getAdjustedNodes() {
        return new ArrayList<>(getBorders().values());
    }

    public static float calculateDistance(float[] v1, float[] v2) {
        return calculateDistanceByHaversineFormula(v1[0], v1[1], v2[0], v2[1]);
    }

    public static float calculateDistanceByHaversineFormula(float lat1, float lon1, float lat2, float lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                   + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                     * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (EARTH_RADIUS_KM * c); // return the distance in kilometers
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return name.equals(country.name) && countryCode.equals(country.countryCode) && Arrays.equals(coordinates, country.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, countryCode);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
