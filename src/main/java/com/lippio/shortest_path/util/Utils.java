package com.lippio.shortest_path.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Utils {

    public static float calculatePy(float[] v1, float[] v2) {
        float x = v1[0] - v2[0];
        float y = v1[1] - v2[1];
        return (float) Math.sqrt((x * x) + (y * y));
    }

}
