package com.lippio.shortest_path.service;

import com.lippio.shortest_path.enums.CountryIdentifier;
import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.pojo.RequestShortestPathData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DijkstraServiceTest {

    private final DijkstraService dijkstraService = ServicesConfiguration.dijkstraService();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource(value = {"NAM:VNM:CCA_3", "NA:VN:ISO_CODE", "NAMIBIA:VIETNAM:NAME"}, delimiter = ':')
    void myDijkstraTest_NAM_TO_VNM(String origin, String destination, CountryIdentifier countryIdentifier) {
        List<String> shortestPath = dijkstraService.getShortestPath(origin, destination, countryIdentifier);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"NAM", "AGO", "COD", "SSD", "SDN", "EGY", "ISR", "JOR", "IRQ", "IRN", "PAK", "IND",
                                 "MMR", "LAO", "VNM"};
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_NAM_TO_VNM_Data() {
        RequestShortestPathData requestShortestPathData = RequestShortestPathData
            .builder()
            .origin("NA")
            .destination("VN")
            .countryIdentifier(CountryIdentifier.ISO_CODE)
            .build();

        List<String> shortestPath = dijkstraService.getShortestPath(requestShortestPathData);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"NAM", "AGO", "COD", "SSD", "SDN", "EGY", "ISR", "JOR", "IRQ", "IRN", "PAK", "IND",
                                 "MMR", "LAO", "VNM"};
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_CZE_TO_ITA() {
        List<String> shortestPath = dijkstraService.getShortestPath("CZE", "ITA", CountryIdentifier.CCA_3);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"CZE", "AUT", "ITA"};
        assertArrayEquals(expectedPath, actualPath);
    }


    @Test
    void myDijkstraTest_USA_TO_ETH() {
        try {
            dijkstraService.getShortestPath("USA", "ETH", CountryIdentifier.CCA_3);
            fail();
        } catch (RestException exception) {
            assertEquals(Errors.PATH_NOT_FOUND.getMessage(), exception.getMessage());
            assertEquals(400, exception.getErrors().getStatus().value());
        }
    }

    @Test
    void myDijkstraTest_ETH_TO_USA() {
        try {
            dijkstraService.getShortestPath("ETH", "USA", CountryIdentifier.CCA_3);
            fail();
        } catch (RestException exception) {
            assertEquals(Errors.PATH_NOT_FOUND.getMessage(), exception.getMessage());
            assertEquals(400, exception.getErrors().getStatus().value());
        }
    }

    @Test
    void myDijkstraTest_ETH_TO_ETH() {
        List<String> shortestPath = dijkstraService.getShortestPath("ETH", "ETH", CountryIdentifier.CCA_3);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"ETH"};
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_IND_TO_PRT() {
        List<String> shortestPath = dijkstraService.getShortestPath("IND", "PRT", CountryIdentifier.CCA_3);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
                                 "PRT"};
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_IND_TO_PRT_caseCheck() {
        List<String> shortestPath = dijkstraService.getShortestPath("InD", "pRT", CountryIdentifier.CCA_3);
        String[] actualPath = shortestPath.toArray(String[]::new);

        String[] expectedPath = {"IND", "PAK", "IRN", "TUR", "BGR", "SRB", "HRV", "SVN", "AUT", "CHE", "FRA", "ESP",
                                 "PRT"};
        assertArrayEquals(expectedPath, actualPath);
    }

    @Test
    void myDijkstraTest_InvalidCountryName() {
        try {
            dijkstraService.getShortestPath("InjD", "pRssT", CountryIdentifier.CCA_3);
            fail();
        } catch (RestException exception) {
            assertEquals("Country not found", exception.getMessage());
            assertEquals(404, exception.getErrors().getStatus().value());
        }
    }
}