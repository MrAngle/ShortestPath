package com.lippio.shortest_path;

import com.lippio.shortest_path.dijkstra.Graph;
import com.lippio.shortest_path.dijkstra.MyGraph;
import com.lippio.shortest_path.dijkstra.MyNode;
import com.lippio.shortest_path.dijkstra.Node;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.DataLoaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
public class DataLoaderTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataLoaderService dataLoaderService;

    @Test
    void restTempTest() {
        Country[] response = restTemplate.getForObject(
                "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", Country[].class);
        System.out.println(response);
    }

    @Test
    void restTempTest2() {
        Set<Country> countries = dataLoaderService.loadData();

        System.out.println(countries);
    }


    @Test
    void dijkstraTest() {
        Set<Country> countries = dataLoaderService.loadData();
        System.out.println(countries);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Graph.calculateShortestPathFromSource(graph, nodeA);

        System.out.println(graph);
    }


    @Test
    void myDijkstraTest() {
        Set<Country> countries = dataLoaderService.loadData();
        System.out.println(countries);

//        Set<Country> filteredCountries =
//                countries.stream().filter(x -> x.getSubregion().equals("Central Europe") || x.getSubregion().equals(
//                        "Southern Europe") ).collect(Collectors.toSet());
//
//        Country pol = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("POL")).findFirst().get();
//        Country cze = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("CZE")).findFirst().get();
//        Country UKR = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("UKR")).findFirst().get();
//        Country DEU = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("DEU")).findFirst().get();
//        Country AUT = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("AUT")).findFirst().get();
//        Country SVK = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("SVK")).findFirst().get();
//        Country HUN = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("HUN")).findFirst().get();
//        Country ITA = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("ITA")).findFirst().get();

        MyGraph graph = new MyGraph();
        Country pol = countries.stream().filter(x -> x.getCountryCode().equalsIgnoreCase("POL")).findFirst().get();

        Country NAM = countries.stream().filter(x -> x.getCountryCode().equals("NAM")).findFirst().get();
        for (Country country: countries) {
            graph.addNode(country);
        }

//        graph.addNode(cze);
//        graph.addNode(UKR);
//        graph.addNode(DEU);
//        graph.addNode(AUT);
//        graph.addNode(SVK);
//        graph.addNode(HUN);
//        graph.addNode(ITA);
        graph = MyGraph.calculateShortestPathFromSource(graph, NAM);

        Country KOR =  countries.stream().filter(x -> x.getCountryCode().equals("VNM")).findFirst().get();
        for (Country country: KOR.getShortestPath()) {
            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + country.getCoordinates());
        }


        System.out.println(graph);
    }

}
