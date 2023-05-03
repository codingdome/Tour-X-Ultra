package at.fhtw.swen2.tourxultra.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildFriendlinessCaluclationImplTest {

    ChildFriendlinessCalculation childFriendlinessCalculation = new ChildFriendlinessCaluclationImpl();

    @Test
    void getTenScore() {
        System.out.println(childFriendlinessCalculation.calculateNewChildFriendliness(1, 10.0, 1000.0));
    }
}