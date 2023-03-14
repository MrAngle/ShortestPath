package com.lippio.shortest_path.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class DijkstraAlgorithm {

    private DijkstraAlgorithm() {}

    public static List<CountryNode> calculateShortestPathFromSource(CountryNode from, CountryNode to) {

        from.setDistance(0f);
        from.getShortestPath().add(from);

        Set<CountryNode> settledNodes = new HashSet<>();
        Set<CountryNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(from);
        while (!unsettledNodes.isEmpty()) {
            Optional<CountryNode> optCurrentNode = getLowestDistanceNode(unsettledNodes);
            if(optCurrentNode.isEmpty()) {
                throw new IllegalArgumentException("Unexpected empty node");
            }
            CountryNode currentNode = optCurrentNode.get();
            unsettledNodes.remove(currentNode);

            // Skip node calculation if shortest Distance (if reached destination) is smaller than current node distance
            if(to.getDistance() < currentNode.getDistance()) {
                continue;
            }

            for (CountryNode adjacentNode : currentNode.getAdjustedNodes()) {
                float edgeWeight = adjacentNode.calculateDistance(currentNode.getCountry());
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return to.getShortestPath();
    }

    private static Optional<CountryNode> getLowestDistanceNode(final Set<CountryNode> unsettledNodes) {
        CountryNode lowestDistanceNode = null;
        float lowestDistance = Float.MAX_VALUE;
        for (CountryNode node : unsettledNodes) {
            float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        if(lowestDistanceNode == null) {
            return Optional.empty();
        }
        return Optional.of(lowestDistanceNode);
    }

    private static void calculateMinimumDistance(final CountryNode evaluationNode,
                                                 float edgeWeigh, final CountryNode sourceNode) {
        float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<CountryNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(evaluationNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}