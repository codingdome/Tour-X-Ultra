package at.fhtw.swen2.tourxultra.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationImplTest {

    InputValidationImpl inputValidation = new InputValidationImpl();

    @Test
    void testIsNumericString() {
        String string = "thisIsNotNumeric";
        assertFalse(inputValidation.isNumeric(string));
    }

    @Test
    void testIsNumericNull() {
        String string = null;
        assertFalse(inputValidation.isNumeric(string));
    }

    @Test
    void thisIsNumericStringAndNumber() {
        String string = "thisIsNotNumeric123";
        assertFalse(inputValidation.isNumeric(string));
    }

    @Test
    void thisIsNumeric() {
        String string = "12345";
        assertTrue(inputValidation.isNumeric(string));
    }

    @Test
    void thisIsEmpty() {
        String string = "";
        assertFalse(inputValidation.isNotEmpty(string));
    }

    @Test
    void thisIsEmptyNull() {
        String string = null;
        assertFalse(inputValidation.isNotEmpty(string));
    }

    @Test
    void thisIsNotEmpty() {
        String string = "iAmNotEmpty";
        assertTrue(inputValidation.isNotEmpty(string));
    }

    @Test
    void thisIsNotEmptyAndDoesNotCareSymbols() {
        String string = "iAmNotEmptyWithNumbers12345";
        assertTrue(inputValidation.isNotEmpty(string));
    }
}