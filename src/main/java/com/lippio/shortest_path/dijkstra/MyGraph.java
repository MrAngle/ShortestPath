package com.lippio.shortest_path.dijkstra;

import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.pojo.NodeRelation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyGraph {

    private Set<Country> nodes = new HashSet<>();

    public void addNode(Country nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters


    public static MyGraph calculateShortestPathFromSource(MyGraph graph, Country source) {
        source.setDistance(0f, source);
//        source.setDistance(source);

        Set<Country> settledNodes = new HashSet<>();
        Set<Country> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);
        while (unsettledNodes.size() != 0) {

            Country currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<String, Country> adjacencyPair:
                    currentNode.getBorders().entrySet()) {
                Country adjacentNode = adjacencyPair.getValue();
                float edgeWeight = NodeRelation.calculateVector(currentNode.getCoordinates(),
                        adjacentNode.getCoordinates());
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Country getLowestDistanceNode(Set <Country> unsettledNodes) {
        Country lowestDistanceNode = null;
        float lowestDistance = Integer.MAX_VALUE;
        for (Country node: unsettledNodes) {
            float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Country evaluationNode,
                                                 float edgeWeigh, Country sourceNode) {
        float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {

            float dist = sourceDistance + NodeRelation.calculateVector(evaluationNode.getCoordinates(),
                    sourceNode.getCoordinates());

            evaluationNode.setDistance(dist, evaluationNode);
            LinkedList<Country> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}