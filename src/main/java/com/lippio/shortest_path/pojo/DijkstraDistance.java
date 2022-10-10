package com.lippio.shortest_path.pojo;


import java.util.List;

public interface DijkstraDistance<T> {

    float calculateDistance(T t);

    List<T> getAdjustedNodes();

}
