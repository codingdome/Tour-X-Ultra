package at.fhtw.swen2.tourxultra.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    @Test
    void logBuilderTest() {
        Log log = Log.builder().id(1L).tour(null).date("23/12/1996").comment("Ein Kommentar").duration(3000).difficulty(5).rating(2).build();
        assertEquals(1L, log.getId());
        assertEquals("Ein Kommentar", log.getComment());
    }

    @Test
    void logBuilderIncompleteTest() {
        Log log = Log.builder().date("23/12/1996").comment("Ein Kommentar").duration(3000).difficulty(5).rating(2).build();
        assertNull(log.getId());
        assertNull(log.getTour());
    }

    @Test
    void logBuilderEmptyTest() {
        Log log = Log.builder().build();
        assertNull(log.getId());
    }
}