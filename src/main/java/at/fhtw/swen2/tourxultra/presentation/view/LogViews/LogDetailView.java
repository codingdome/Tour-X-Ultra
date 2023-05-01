package at.fhtw.swen2.tourxultra.presentation.view.LogViews;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.LogDetailViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.service.util.InputValidation;
import at.fhtw.swen2.tourxultra.service.util.InputValidationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class LogDetailView implements Initializable {

    private final InputValidation inputValidation = new InputValidationImpl();
    @FXML
    public Text t_tour_name;
    @FXML
    public Text t_tour_description;
    @FXML
    public Text t_date;
    @FXML
    public TextField tf_date;
    @FXML
    public Text t_comment;
    @FXML
    public TextField tf_comment;
    @FXML
    public Text t_difficulty;
    @FXML
    public TextField tf_difficulty;
    @FXML
    public Text t_duration;
    @FXML
    public TextField tf_duration;
    @FXML
    public Text t_rating;
    @FXML
    public TextField tf_rating;
    @FXML
    public Text t_feedback;

    @Autowired
    TourDetailViewModel tourDetailViewModel;

    @Autowired
    LogDetailViewModel logDetailViewModel;

    @Autowired
    ApplicationView applicationView;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        t_tour_name.textProperty().bindBidirectional(logDetailViewModel.tour_nameProperty());
        t_tour_description.textProperty().bindBidirectional(logDetailViewModel.tour_descriptionProperty());
        t_date.textProperty().bindBidirectional(logDetailViewModel.dateProperty());
        tf_date.textProperty().bindBidirectional(logDetailViewModel.dateProperty());
        t_comment.textProperty().bindBidirectional(logDetailViewModel.commentProperty());
        tf_comment.textProperty().bindBidirectional(logDetailViewModel.commentProperty());
        t_difficulty.textProperty().bindBidirectional(logDetailViewModel.difficultyProperty(), new NumberStringConverter());
        tf_difficulty.textProperty().bindBidirectional(logDetailViewModel.difficultyProperty(), new NumberStringConverter());
        t_duration.textProperty().bindBidirectional(logDetailViewModel.durationProperty(), new NumberStringConverter());
        tf_duration.textProperty().bindBidirectional(logDetailViewModel.durationProperty(), new NumberStringConverter());
        t_rating.textProperty().bindBidirectional(logDetailViewModel.ratingProperty(), new NumberStringConverter());
        tf_rating.textProperty().bindBidirectional(logDetailViewModel.ratingProperty(), new NumberStringConverter());
        t_feedback.textProperty().bindBidirectional(logDetailViewModel.feedbackProperty());
    }

    public void editLogButtonAction(ActionEvent actionEvent) {
        unlockInput();
    }

    public void saveChangesButtonAction(ActionEvent actionEvent) {
        t_feedback.setText("");
        List<String> feedbackList = inputValidation.validateNewLogInput(tf_date.getText(), tf_comment.getText(), tf_difficulty.getText(), tf_duration.getText(), tf_rating.getText());
        if (feedbackList.isEmpty()) {
            logDetailViewModel.updateLog();
            lockInput();
        } else {
            for (String feedback : feedbackList) {
                System.out.println(feedback);
                String existingText = t_feedback.getText();
                t_feedback.setText(existingText + feedback);
            }
        }
    }

    public void deleteLogButtonAction(ActionEvent actionEvent) {
        logDetailViewModel.deleteLog();
    }

    private void unlockInput() {
        tf_date.setDisable(false);
        tf_comment.setDisable(false);
        tf_duration.setDisable(false);
        tf_rating.setDisable(false);
        tf_difficulty.setDisable(false);
    }

    private void lockInput() {
        tf_date.setDisable(true);
        tf_comment.setDisable(true);
        tf_duration.setDisable(true);
        tf_rating.setDisable(true);
        tf_difficulty.setDisable(true);
    }
}
