package at.fhtw.swen2.tourxultra.service.util;

import java.util.List;

public interface InputValidation {

    List<String> validateNewTourInput(String name, String description, String departure, String arrival, String transport, String distance, String duration);

    List<String> validateNewLogInput(String date, String comment, String difficulty, String duration, String rating);
}
