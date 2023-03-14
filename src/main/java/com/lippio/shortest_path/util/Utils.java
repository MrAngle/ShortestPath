//package com.lippio.shortest_path.util;
//
//import lombok.experimental.UtilityClass;
//
//@UtilityClass
//public final class Utils {
//
//
//    private static final int EARTH_RADIUS_KM = 6371; // Earth radius in kilometers
//
//    public static float calculateDistance(float[] v1, float[] v2) {
//        return calculateDistanceByHaversineFormula(v1[0], v1[1], v2[0], v2[1]);
//    }
//
//    public static float calculateDistanceByHaversineFormula(float lat1, float lon1, float lat2, float lon2) {
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
//                   + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                     * Math.sin(dLon / 2) * Math.sin(dLon / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return (float) (EARTH_RADIUS_KM * c); // return the distance in kilometers
//    }
//
//}
