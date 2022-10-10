package com.lippio.shortest_path.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lippio.shortest_path.jackson.CountryNameDeserializer;
import com.lippio.shortest_path.jackson.NodeRelationDeserializer;
import com.lippio.shortest_path.util.Utils;
import lombok.AllArgsConstructor;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country implements DijkstraDistance<Country> {

    @JsonDeserialize(using = CountryNameDeserializer.class, as=String.class)
    @JsonProperty("name")
    private String name;

    @JsonProperty("cca3")
    private String countryCode;

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
        return Utils.calculateVector(this.getCoordinates(), country.getCoordinates());
    }

    @Override
    public List<Country> getAdjustedNodes() {
        return new ArrayList<>(getBorders().values());
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
