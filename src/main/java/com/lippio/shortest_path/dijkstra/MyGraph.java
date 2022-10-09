package com.lippio.shortest_path.dijkstra;

import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.pojo.NodeRelation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyGraph {

    public static int nodesOperationsNumber = 0;

    public static Country calculateShortestPathFromSource(MyGraph graph, Country from, Country to) {
//        float shortestDistanceToDestination = Float.MAX_VALUE;

        from.setDistance(0f);
        from.getShortestPath().add(from);

        Set<Country> settledNodes = new HashSet<>();
        Set<Country> unsettledNodes = new HashSet<>();

        unsettledNodes.add(from);
        while (!unsettledNodes.isEmpty()) {
            nodesOperationsNumber++;
            Country currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            // Skip node calculation if shortest Distance (if reached destination) is smaller than current node distance
            if(to.getDistance() < currentNode.getDistance()) {
                continue;
            }

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
        return to;
    }

    private static Country getLowestDistanceNode(Set <Country> unsettledNodes) {
        Country lowestDistanceNode = null;
        float lowestDistance = Float.MAX_VALUE;
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

            evaluationNode.setDistance(dist);
            LinkedList<Country> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(evaluationNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}