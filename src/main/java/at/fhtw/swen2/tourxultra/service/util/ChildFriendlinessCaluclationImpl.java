package at.fhtw.swen2.tourxultra.service.util;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ChildFriendlinessCaluclationImpl implements ChildFriendlinessCalculation {

    @Override
    public Integer calculateNewChildFriendliness(Integer distanceInMeters, Double avgDifficulty, Double avgDuration) {

        // Convert distance to meters and duration to minutes
        double distanceInKm = distanceInMeters / 1000.0;
        double durationInHours = avgDuration / 60.0;

        // Convert average speed to km/h for the speed score calculation
        double averageSpeedInKmh = (distanceInKm / durationInHours);

        // Calculate speed score
        int THRESHOLD = 6;
        double speedScore = 10 + (averageSpeedInKmh - THRESHOLD) / ((double) THRESHOLD / 9.0);
        speedScore = Math.min(Math.max(speedScore, 1), 10);

        // Calculate new child friendliness score

        return (int) Math.round((avgDifficulty + speedScore) / 2.0);
    }
}
