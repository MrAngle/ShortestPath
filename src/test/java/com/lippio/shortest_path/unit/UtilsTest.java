package com.lippio.shortest_path.unit;

import com.lippio.shortest_path.pojo.Country;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class UtilsTest {

    @Test
    void calculateVectorTest() {
        Vector<Float> v1 = new Vector<>();
        v1.add(52f);
        v1.add(20f);

        Vector<Float> v2 = new Vector<>();
        v2.add(49.75f);
        v2.add(15.5f);
        float magnitude = Country.calculateVector(v1, v2);
        System.out.println(magnitude);

        Vector<Float> v3 = new Vector<>();
        v3.add(-52f);
        v3.add(20f);

        Vector<Float> v4 = new Vector<>();
        v4.add(49.75f);
        v4.add(15.5f);
        float magnitude2 = Country.calculateVector(v3, v4);
        System.out.println(magnitude2);

        Vector<Float> v5 = new Vector<>();
        v5.add(-52f);
        v5.add(20f);

        Vector<Float> v6 = new Vector<>();
        v6.add(49.75f);
        v6.add(-15.5f);
        float magnitude3 = Country.calculateVector(v5, v6);
        System.out.println(magnitude3);


        // UK
        Vector<Float> v7 = new Vector<>();
        v7.add(52f);
        v7.add(20f);

        Vector<Float> v8 = new Vector<>();
        v8.add(49f);
        v8.add(32f);
        float magnitude4 = Country.calculateVector(v7, v8);
        System.out.println(magnitude4);

    }
}
