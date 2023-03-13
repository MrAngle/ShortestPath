//package com.lippio.shortest_path;
//
//import com.lippio.shortest_path.dijkstra.CountryNode;
//import com.lippio.shortest_path.errors.RestException;
//import com.lippio.shortest_path.service.DijkstraService;
//import com.lippio.shortest_path.service.DijkstraServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
//public class DataLoaderTest {
//
//    @Autowired
//    DijkstraService dijkstraService;
//
//
//    @Test
//    void myDijkstraTest_NAM_TO_VNM() {
//        List<String> shortestPath = dijkstraService.getShortestPath("NAM", "VNM");
//        String[] actualPath = shortestPath.toArray(String[]::new);
//
//        String[] expectedPath = {"NAM", "AGO", "COD", "SSD", "SDN", "EGY", "ISR", "JOR", "IRQ", "IRN", "PAK", "IND",
//                "MMR", "LAO", "VNM"};
//        assertArrayEquals(expectedPath, actualPath);
//    }
//
//    @Test
//    void myDijkstraTest_CZE_TO_ITA() {
//        List<String> shortestPath = dijkstraService.getShortestPath("CZE", "ITA");
//        String[] actualPath = shortestPath.toArray(String[]::new);
//
//        String[] expectedPath = { "CZE" , "AUT" , "ITA" };
//        assertArrayEquals(expectedPath, actualPath);
//
//    }
//
//
//    @Test
//    void myDijkstraTest_USA_TO_ETH() {
//        try {
//            dijkstraService.getShortestPath("USA", "ETH");
//            fail();
//        } catch (RestException exception) {
//            assertEquals("Path not found", exception.getMessage());
//            assertEquals(400, exception.getErrors().getStatus().value());
//        }
//    }
//
//    @Test
//    void myDijkstraTest_ETH_TO_USA() {
//        try {
//            dijkstraService.getShortestPath("ETH", "USA");
//            fail();
//        } catch (RestException exception) {
//            assertEquals("Path not found", exception.getMessage());
//            assertEquals(400, exception.getErrors().getStatus().value());
//        }
//    }
//
//    @Test
//    void myDijkstraTest_ETH_TO_ETH() {
//        List<String> shortestPath = dijkstraService.getShortestPath("ETH", "ETH");
//        String[] actualPath = shortestPath.toArray(String[]::new);
//
//        String[] expectedPath = { "ETH" };
//        assertArrayEquals(expectedPath, actualPath);
//    }
//
//    @Test
//    void myDijkstraTest_IND_TO_PRT() {
//        List<String> shortestPath = dijkstraService.getShortestPath("IND", "PRT");
//        String[] actualPath = shortestPath.toArray(String[]::new);
//
//        String[] expectedPath = { "IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
//                "PRT" };
//        assertArrayEquals(expectedPath, actualPath);
//    }
//
//    @Test
//    void myDijkstraTest_IND_TO_PRT_caseCheck() {
//        List<String> shortestPath = dijkstraService.getShortestPath("InD", "pRT");
//        String[] actualPath = shortestPath.toArray(String[]::new);
//
//        String[] expectedPath = { "IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
//                "PRT" };
//        assertArrayEquals(expectedPath, actualPath);
//    }
//
//    @Test
//    void myDijkstraTest_InvalidCountryName() {
//        try {
//            dijkstraService.getShortestPath("InjD", "pRssT");
//            fail();
//        } catch (RestException exception) {
//            assertEquals("Country not found", exception.getMessage());
//            assertEquals(400, exception.getErrors().getStatus().value());
//        }
//    }
//
//}
