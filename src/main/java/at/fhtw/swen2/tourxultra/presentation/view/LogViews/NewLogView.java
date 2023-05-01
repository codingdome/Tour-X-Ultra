package at.fhtw.swen2.tourxultra.presentation.view.LogViews;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.NewLogViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.NewTourViewModel;
import at.fhtw.swen2.tourxultra.service.util.InputValidation;
import at.fhtw.swen2.tourxultra.service.util.InputValidationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class NewLogView implements Initializable {

    private final InputValidation inputValidation = new InputValidationImpl();

    @Autowired
    private NewLogViewModel newLogViewModel;
    @FXML
    public TextField tf_date;
    @FXML
    public TextField tf_comment;
    @FXML
    public TextField tf_difficulty;
    @FXML
    public TextField tf_duration;
    @FXML
    public TextField tf_rating;
    @FXML
    public Label l_feedback;


    @Override
    public void initialize(URL location, ResourceBundle rb) {

        tf_date.textProperty().bindBidirectional(newLogViewModel.dateProperty());
        tf_comment.textProperty().bindBidirectional(newLogViewModel.commentProperty());
        tf_difficulty.textProperty().bindBidirectional(newLogViewModel.difficultyProperty(), new NumberStringConverter());
        tf_duration.textProperty().bindBidirectional(newLogViewModel.durationProperty(), new NumberStringConverter());
        tf_rating.textProperty().bindBidirectional(newLogViewModel.ratingProperty(), new NumberStringConverter());

        tf_difficulty.setText("");
        tf_duration.setText("");
        tf_rating.setText("");
    }

    public void submitButtonAction() {
        l_feedback.setText("");
        List<String> feedbackList = inputValidation.validateNewLogInput(tf_date.getText(), tf_comment.getText(), tf_difficulty.getText(), tf_duration.getText(), tf_rating.getText());
        if (feedbackList.isEmpty()) {
            newLogViewModel.addNewLog();
        } else {
            for (String feedback : feedbackList) {
                System.out.println(feedback);
                String existingText = l_feedback.getText();
                l_feedback.setText(existingText + feedback);
            }
        }
    }

    public void setDateToToday(ActionEvent actionEvent) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        tf_date.setText(formattedDate);
    }
}
