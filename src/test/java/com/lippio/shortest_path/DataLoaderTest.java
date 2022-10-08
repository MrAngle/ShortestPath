package com.lippio.shortest_path;

import com.lippio.shortest_path.pojo.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
public class DataLoaderTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void restTempTest() {
        Country[] response = restTemplate.getForObject(
                "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", Country[].class);
        System.out.println(response);
    }


}
