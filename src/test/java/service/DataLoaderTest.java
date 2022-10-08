package service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes={com.lippio.shortest_path.ShortestPathApplication.class})
public class DataLoaderTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void dataLoaderTest() {
        Object quote = restTemplate.getForObject(
                "https://raw.githubusercontent.com/mledoze/countries/master/countries.jsonm", Object.class);
        System.out.println(quote);
    }

}
