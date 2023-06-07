package at.fhtw.swen2.tourxultra.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildFriendlinessCaluclationImplTest {

    ChildFriendlinessCalculation childFriendlinessCalculation = new ChildFriendlinessCaluclationImpl();

    @Test
    void getTenScore() {
        System.out.println(childFriendlinessCalculation.calculateNewChildFriendliness(1, 10.0, 1000.0));
    }

    @Test
    void getResult2() {
        System.out.println(childFriendlinessCalculation.calculateNewChildFriendliness(500, 5.0, 60.0));
    }

    @Test
    void getResultEasy() {
        System.out.println(childFriendlinessCalculation.calculateNewChildFriendliness(10, 1.0, 1.0));
    }

    @Test
    void getResult() {
        System.out.println(childFriendlinessCalculation.calculateNewChildFriendliness(2000, 5.0, 30.0));
    }
}