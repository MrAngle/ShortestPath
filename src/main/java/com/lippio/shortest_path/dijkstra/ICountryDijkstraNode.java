package com.lippio.shortest_path.dijkstra;

import com.lippio.shortest_path.pojo.Country;

import java.util.LinkedList;
import java.util.List;


// TODO: Make it generic if needed
public interface ICountryDijkstraNode {

    String getName();

    List<CountryNode> getAdjustedNodes();

    float getDistance();

    List<CountryNode> getShortestPath();

    void setDistance(float distance);

    void setShortestPath(LinkedList<CountryNode> shortestPath);

    float calculateDistance(Country node);

}
