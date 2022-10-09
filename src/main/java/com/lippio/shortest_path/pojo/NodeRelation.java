package com.lippio.shortest_path.pojo;

import lombok.AllArgsConstructor;

import java.util.Vector;

@AllArgsConstructor
public class NodeRelation {

    public static final boolean USE_VECTOR_LENGTH = true;

    private Country country;
    private float pathValue;

    public static NodeRelation calculateNodeRelation(Country src, Country foreign) {

        float pathValue = 1;
        if(USE_VECTOR_LENGTH) {
            pathValue = calculateVector(src.getCoordinates(), foreign.getCoordinates());
        }

        return new NodeRelation(foreign, pathValue);
    }


    public static float calculateVector(Vector<Float> v1, Vector<Float> v2) {
        float x = v1.get(0) - v2.get(0);
        float y = v1.get(1) - v2.get(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public static float calculateVector(float[] v1, float[] v2) {
        float x = v1[0] - v2[0];
        float y = v1[1] - v2[1];
        return (float) Math.sqrt((x * x) + (y * y));
    }
}
