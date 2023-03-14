package com.lippio.shortest_path.dijkstra;

import com.lippio.shortest_path.pojo.Country;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type Country node.
 */
// TODO: Make it generic if needed
@Getter
@Setter
public final class CountryNode implements ICountryDijkstraNode {

    private final Country country;
    @Setter(AccessLevel.NONE)
    private float distance = Float.MAX_VALUE;
    private List<CountryNode> shortestPath = new LinkedList<>();
    private Set<CountryNode> adjustedNodes = new HashSet<>();

    private CountryNode(Country country) {
        this.country = country;
    }

    @Override
    public String getName() {
        return country.getName();
    }

    public List<CountryNode> getShortestPath() {
        return shortestPath;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public void setShortestPath(LinkedList<CountryNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public float calculateDistance(Country node) {
        return country.calculateDistance(node);
    }

    /**
     * Map Country set to Node set.
     *
     * Its necessary to make dijkstra calculation action unit and to separate country class logic.
     *
     * @param countries
     * @return
     */
    public static Set<CountryNode> toCountryNodes(Set<Country> countries) {
        Set<CountryNode> countryNodes = new HashSet<>();
        for (Country country: countries) {
            countryNodes.add(new CountryNode(country));
        }

        for (CountryNode countryNode : countryNodes) {
            Set<CountryNode> adjustedNodes = countryNode.getAdjustedNodes();

            for (Country country : countryNode.getCountry().getAdjustedNodes()) {
                adjustedNodes.add(countryNodes.stream()
                        .filter(x -> country.getCountryCode().equalsIgnoreCase(x.getCountry().getCountryCode()))
                                .findFirst()
                                .orElseThrow());
            }
        }
        return countryNodes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountryNode that = (CountryNode) o;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
