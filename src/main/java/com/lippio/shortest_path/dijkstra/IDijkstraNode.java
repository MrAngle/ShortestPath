package com.lippio.shortest_path.dijkstra;


import java.util.List;

public interface IDijkstraNode<T> {

    float calculateDistance(T t);

    List<T> getAdjustedNodes();

}
