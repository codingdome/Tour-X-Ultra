package at.fhtw.swen2.tourxultra.service.util;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ChildFriendlinessCaluclationImpl implements ChildFriendlinessCalculation {

    @Override
    public Integer calculateNewChildFriendliness(Integer distanceInMeters, Double avgDifficulty, Double avgDuration) {

        return (int) (distanceInMeters * avgDifficulty * avgDuration);

//        int maxDistance = 1000; // Maximum distance considered child-friendly
//        double maxDifficulty = 10.0; // Maximum difficulty score
//        int maxDuration = 120; // Maximum child-friendly duration
//
//        double normalizedDistance = distanceInMeters / (double) maxDistance;
//        System.out.println(normalizedDistance);
//        double inverseDifficulty = maxDifficulty - avgDifficulty;
//        System.out.println(inverseDifficulty);
//        double normalizedDuration = avgDuration / (double) maxDuration;
//        System.out.println(normalizedDuration);
//        double average = (normalizedDistance + inverseDifficulty + normalizedDuration) / 3.0;
//        System.out.println(average);
//
//        return (int) average;

//        // Convert distance to meters and duration to minutes
//        double distanceInKm = distanceInMeters / 1000.0;
//        double durationInHours = avgDuration / 60.0;
//
//        // Convert average speed to km/h for the speed score calculation
//        double averageSpeedInKmh = (distanceInKm / durationInHours);
//
//        // Calculate speed score
//        int THRESHOLD = 6;
//        double speedScore = 10 + (averageSpeedInKmh - THRESHOLD) / ((double) THRESHOLD / 9.0);
//        speedScore = Math.min(Math.max(speedScore, 1), 10);
//
//        // Calculate new child friendliness score
//
//        return (int) Math.round((avgDifficulty + speedScore) / 2.0);
    }
}
