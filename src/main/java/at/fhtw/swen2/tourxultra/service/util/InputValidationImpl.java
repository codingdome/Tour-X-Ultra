package at.fhtw.swen2.tourxultra.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InputValidationImpl implements InputValidation {

    private enum ErrorMessage {
        NAME_ERROR("Please fill out name. "), DESCRIPTION_ERROR("Please fill out description. "), DEPARTURE_ERROR("Please fill out departure. "), ARRIVAL_ERROR("Please fill out arrival. "), TRANSPORT_ERROR("Please fill out transport. "), DISTANCE_ERROR("Please fill out distance & make sure to only use digits. "), DURATION_ERROR("Please fill out duration & make sure to only use digits. ");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }
    }

    private enum FeedbackLogMessage {
        DATE_ERROR("Please fill out date and make sure it is in dd/mm/yyyy format. "), COMMENT_ERROR("Please fill out comment. "), DIFFICULTY_ERROR("Please fill out difficulty and give it a value between 1-10 (1 = easy). "), DURATION_ERROR("Please fill out duration. Make sure to only use digits between 0-9. "), RATING_ERROR("Please fill out rating. Give it a value between 1-5 (1 = very good)");

        private final String message;

        FeedbackLogMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public List<String> validateNewTourInput(String name, String description, String departure, String arrival, String transport, String distance, String duration) {
        List<String> feedback = new ArrayList<>();

        String[] inputStrings = {name, description, departure, arrival, transport, distance, duration};
        String[] errorMessages = {ErrorMessage.NAME_ERROR.message, ErrorMessage.DESCRIPTION_ERROR.message, ErrorMessage.DEPARTURE_ERROR.message, ErrorMessage.ARRIVAL_ERROR.message, ErrorMessage.TRANSPORT_ERROR.message, ErrorMessage.DISTANCE_ERROR.message, ErrorMessage.DURATION_ERROR.message};

        for (int i = 0; i < inputStrings.length; i++) {
            String input = inputStrings[i];
            String errorMessage = errorMessages[i];
            if (!isNotEmpty(input)) {
                feedback.add(errorMessage);
            } else if (i == 5 && !isNumeric(input)) {
                feedback.add(errorMessage);
            } else if (i == 6 && !isNumeric(input)) {
                feedback.add(errorMessage);
            }
        }
        return feedback;
    }

    @Override
    public List<String> validateNewLogInput(String date, String comment, String difficulty, String duration, String rating) {
        List<String> feedback = new ArrayList<>();
        String[] inputStrings = {date, comment, difficulty, duration, rating};
        String[] feedbackMessages = {FeedbackLogMessage.DATE_ERROR.message, FeedbackLogMessage.COMMENT_ERROR.message, FeedbackLogMessage.DIFFICULTY_ERROR.message, FeedbackLogMessage.DURATION_ERROR.message, FeedbackLogMessage.RATING_ERROR.message};

        for (int i = 0; i < inputStrings.length; i++) {
            String input = inputStrings[i];
            String feedbackMessage = feedbackMessages[i];
            if (!isNotEmpty(input)) {
                feedback.add(feedbackMessage);
            } else if (i == 0 && !isValidDate(input)) {
                feedback.add(feedbackMessage);
            } else if (i == 2 && !isBetweenOneAndTen(input)) {
                feedback.add(feedbackMessage);
            } else if (i == 3 && !isNumeric(input)) {
                feedback.add(feedbackMessage);
            } else if (i == 4 && !isBetweenOneAndFive(input)) {
                feedback.add(feedbackMessage);
            }
        }
        return feedback;
    }

    boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }

    boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBetweenOneAndFive(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            int value = Integer.parseInt(input);
            return value >= 1 && value <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBetweenOneAndTen(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            int value = Integer.parseInt(input);
            return value >= 1 && value <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false); // do not allow lenient parsing

        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}
