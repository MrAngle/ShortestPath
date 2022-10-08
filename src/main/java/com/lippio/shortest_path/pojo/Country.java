package com.lippio.shortest_path.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lippio.shortest_path.dijkstra.MyNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {



    @JsonDeserialize(using = CountryNameDeserializer.class, as=String.class)
    @JsonProperty("name")
    private String name;

    @JsonProperty("cca3")
    private String countryCode;

    private String region;
    private String subregion;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = NodeRelationDeserializer.class, as=Map.class)
    private Map<String, Country> borders;
    private String area;

    @JsonProperty("latlng")
    private Vector<Float> coordinates;

    public static void connectAllCountries(Set<Country> countries) {
        for (Country country: countries) {
            if(country.getBorders() == null && country.getBorders().isEmpty()) {
                continue;
            }

            for (Map.Entry<String, Country> entry : country.getBorders().entrySet()) {
                entry.setValue(
                        countries.stream().filter(x ->
                        entry.getKey().equals(x.getCountryCode())).findFirst().orElseThrow());
            }
        }
    }

    public static float calculateVector(Vector<Float> v1, Vector<Float> v2) {
        float x = v1.get(0) - v2.get(0);
        float y = v1.get(1) - v2.get(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    // DJIKSTRA

    private Integer distance = Integer.MAX_VALUE;
    private List<Country> shortestPath = new LinkedList<>();

    public void setDistance(int i) {
        distance = i;
    }

    public List<Country> getShortestPath() {
        return shortestPath;
    }
}
