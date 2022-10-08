package com.lippio.shortest_path.dijkstra;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MyNode {

    private String name;

    private List<MyNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<MyNode, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(MyNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public MyNode(String name) {
        this.name = name;
    }


}
