package at.fhtw.swen2.tourxultra.service.util;

public interface ChildFriendlinessCalculation {
    Integer calculateNewChildFriendliness(Integer distance, Double avgDifficulty, Double avgDuration);
}
