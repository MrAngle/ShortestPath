package com.lippio.shortest_path.service;

import java.util.List;

public interface DijkstraService {

    List<String> getShortestPath(String from, String to);

}
