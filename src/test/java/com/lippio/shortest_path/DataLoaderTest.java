package com.lippio.shortest_path;

import com.lippio.shortest_path.dijkstra.MyGraph;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.DataLoaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
public class DataLoaderTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataLoaderService dataLoaderService;

//    @Test
//    void restTempTest() {
//        Country[] response = restTemplate.getForObject(
//                "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", Country[].class);
//        System.out.println(response);
//    }



    @Test
    void myDijkstraTest_NAM_TO_VNM() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();
        Country NAM = countries.stream().filter(x -> x.getCountryCode().equals("NAM")).findFirst().get();
        Country VNM = countries.stream().filter(x -> x.getCountryCode().equals("VNM")).findFirst().get();


        VNM = MyGraph.calculateShortestPathFromSource(graph, NAM, VNM);


        for (Country country: VNM.getShortestPath()) {
            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + Arrays.toString(country.getCoordinates()));
        }
        String[] expectedPath = { "NAM", "AGO", "COD", "SSD", "SDN", "EGY", "ISR", "JOR", "IRQ", "IRN", "PAK", "IND",
                "MMR", "LAO", "VNM" };
        System.out.println(MyGraph.nodesOperationsNumber);
        assertArrayEquals(expectedPath, VNM.getShortestPathAsArray());
    }



    @Test
    void myDijkstraTest_CZE_TO_ITA() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();
        Country CZE = countries.stream().filter(x -> x.getCountryCode().equals("CZE")).findFirst().get();
        Country ITA = countries.stream().filter(x -> x.getCountryCode().equals("ITA")).findFirst().get();

        ITA = MyGraph.calculateShortestPathFromSource(graph, CZE, ITA);


        for (Country country: ITA.getShortestPath()) {
            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + country.getCoordinates());
        }
        String[] expectedPath = { "CZE" , "AUT" , "ITA" };
        System.out.println(MyGraph.nodesOperationsNumber);
        assertArrayEquals(expectedPath, ITA.getShortestPathAsArray());

    }


    @Test
    void myDijkstraTest_USA_TO_ETH() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();

        Country USA = countries.stream().filter(x -> x.getCountryCode().equals("USA")).findFirst().get();
        Country ETH = countries.stream().filter(x -> x.getCountryCode().equals("ETH")).findFirst().get();

        ETH = MyGraph.calculateShortestPathFromSource(graph, USA, ETH);


        for (Country country: ETH.getShortestPath()) {
            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + country.getCoordinates());
        }
        System.out.println(MyGraph.nodesOperationsNumber);
        assertEquals( 0, ETH.getShortestPathAsArray().length);
    }

    @Test
    void myDijkstraTest_ETH_TO_USA() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();

        Country ETH = countries.stream().filter(x -> x.getCountryCode().equals("ETH")).findFirst().get();
        Country USA = countries.stream().filter(x -> x.getCountryCode().equals("USA")).findFirst().get();

        USA = MyGraph.calculateShortestPathFromSource(graph, ETH, USA);

        System.out.println(MyGraph.nodesOperationsNumber);
        assertEquals( 0, USA.getShortestPathAsArray().length);
    }

    @Test
    void myDijkstraTest_ETH_TO_GBR() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();

        Country ETH = countries.stream().filter(x -> x.getCountryCode().equals("ETH")).findFirst().get();
        Country GBR = countries.stream().filter(x -> x.getCountryCode().equals("GBR")).findFirst().get();

        GBR = MyGraph.calculateShortestPathFromSource(graph, ETH, GBR);

        System.out.println(MyGraph.nodesOperationsNumber);
        assertEquals( 0, GBR.getShortestPathAsArray().length);
    }

    @Test
    void myDijkstraTest_IND_TO_PRT() {
        Set<Country> countries = dataLoaderService.loadData();

        MyGraph graph = new MyGraph();

        Country IND = countries.stream().filter(x -> x.getCountryCode().equals("IND")).findFirst().get();
        Country PRT = countries.stream().filter(x -> x.getCountryCode().equals("PRT")).findFirst().get();

        PRT = MyGraph.calculateShortestPathFromSource(graph, IND, PRT);

        for (Country country: PRT.getShortestPath()) {
            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + country.getCoordinates());
        }
        String[] expectedPath = { "IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
                "PRT" };
        System.out.println(MyGraph.nodesOperationsNumber);
        assertArrayEquals(expectedPath, PRT.getShortestPathAsArray());
    }

}
