package com.lippio.shortest_path;

import com.lippio.shortest_path.dijkstra.DijkstraAlgorithm;
import com.lippio.shortest_path.service.DataLoaderService;
import com.lippio.shortest_path.service.DijkstraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
public class DataLoaderTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataLoaderService dataLoaderService;

    @Autowired
    DijkstraService dijkstraService;

//    @Test
//    void restTempTest() {
//        Country[] response = restTemplate.getForObject(
//                "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", Country[].class);
//        System.out.println(response);
//    }

    @Test
    void myDijkstraTest_NAM_TO_VNM() {
        List<String> shortestPath = dijkstraService.getShortestPath("NAM", "VNM");
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"NAM", "AGO", "COD", "SSD", "SDN", "EGY", "ISR", "JOR", "IRQ", "IRN", "PAK", "IND",
                "MMR", "LAO", "VNM"};
        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_CZE_TO_ITA() {
        List<String> shortestPath = dijkstraService.getShortestPath("CZE", "ITA");
        String[] actualPath = shortestPath.toArray(String[]::new);

//        for (ICountryDijkstraNode country: node.getShortestPath()) {
////            System.out.println(country.getName() + " - " + country.getDistance() + " - " + country.getCountryCode() + " - " + country.getCoordinates());
//            System.out.println(country.getName() + " - " + country.getDistance());
//        }
        String[] expectedPath = { "CZE" , "AUT" , "ITA" };
        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertArrayEquals(expectedPath, actualPath);

    }


    @Test
    void myDijkstraTest_USA_TO_ETH() {
        List<String> shortestPath = dijkstraService.getShortestPath("USA", "ETH");

        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertEquals( 0, shortestPath.size());
    }

    @Test
    void myDijkstraTest_ETH_TO_USA() {
        List<String> shortestPath = dijkstraService.getShortestPath("ETH", "USA");

        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertEquals( 0, shortestPath.size());
    }

    @Test
    void myDijkstraTest_ETH_TO_GBR() {
        List<String> shortestPath = dijkstraService.getShortestPath("ETH", "GBR");

        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertEquals( 0, shortestPath.size());
    }

    @Test
    void myDijkstraTest_IND_TO_PRT() {
        List<String> shortestPath = dijkstraService.getShortestPath("IND", "PRT");
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = { "IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
                "PRT" };
        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_IND_TO_PRT_caseCheck() {
        List<String> shortestPath = dijkstraService.getShortestPath("InD", "pRT");
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = { "IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
                "PRT" };
        System.out.println(DijkstraAlgorithm.nodesOperationsNumber);
        assertArrayEquals(expectedPath, actualPath);
    }

}
