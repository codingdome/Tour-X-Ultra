package at.fhtw.swen2.tourxultra.presentation.view.TourViews;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.NewTourViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.service.io.ImportExportService;
import at.fhtw.swen2.tourxultra.service.util.InputValidation;
import at.fhtw.swen2.tourxultra.service.util.InputValidationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class TourDetailView implements Initializable {

    private final InputValidation inputValidation = new InputValidationImpl();

    @Autowired
    TourDetailViewModel tourDetailViewModel;

    @Autowired
    ApplicationView applicationView;

    @FXML
    public Text t_name;
    @FXML
    public TextField tf_name;
    @FXML
    public Text t_description;
    @FXML
    public TextField tf_description;
    @FXML
    public Text t_departure;
    @FXML
    public TextField tf_departure;
    @FXML
    public Text t_arrival;
    @FXML
    public TextField tf_arrival;
    @FXML
    public Text t_transport;
    @FXML
    public TextField tf_transport;
    @FXML
    public Text t_distance;
    @FXML
    public TextField tf_distance;
    @FXML
    public Text t_duration;
    @FXML
    public TextField tf_duration;
    @FXML
    public Text t_popularity;
    @FXML
    public Text t_childFriendliness;
    @FXML
    public Text t_feedback;

    @FXML
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        t_name.textProperty().bindBidirectional(tourDetailViewModel.nameProperty());
        tf_name.textProperty().bindBidirectional(tourDetailViewModel.nameProperty());
        t_description.textProperty().bindBidirectional(tourDetailViewModel.descriptionProperty());
        tf_description.textProperty().bindBidirectional(tourDetailViewModel.descriptionProperty());
        t_departure.textProperty().bindBidirectional(tourDetailViewModel.departureProperty());
        tf_departure.textProperty().bindBidirectional(tourDetailViewModel.departureProperty());
        t_arrival.textProperty().bindBidirectional(tourDetailViewModel.arrivalProperty());
        tf_arrival.textProperty().bindBidirectional(tourDetailViewModel.arrivalProperty());
        t_transport.textProperty().bindBidirectional(tourDetailViewModel.transportProperty());
        tf_transport.textProperty().bindBidirectional(tourDetailViewModel.transportProperty());
        t_distance.textProperty().bindBidirectional(tourDetailViewModel.distanceProperty(), new NumberStringConverter());
        tf_distance.textProperty().bindBidirectional(tourDetailViewModel.distanceProperty(), new NumberStringConverter());
        t_duration.textProperty().bindBidirectional(tourDetailViewModel.durationProperty(), new NumberStringConverter());
        tf_duration.textProperty().bindBidirectional(tourDetailViewModel.durationProperty(), new NumberStringConverter());
        t_popularity.textProperty().bindBidirectional(tourDetailViewModel.popularityProperty(), new NumberStringConverter());
        t_childFriendliness.textProperty().bindBidirectional(tourDetailViewModel.childFriendlinessProperty(), new NumberStringConverter());
        t_feedback.textProperty().bindBidirectional(tourDetailViewModel.feedbackProperty());
    }

    public void editTourButtonAction(ActionEvent actionEvent) {
        unlockInput();
    }

    public void saveChangesButtonAction(ActionEvent actionEvent) {
        t_feedback.setText("");
        List<String> feedbackList = inputValidation.validateNewTourInput(tf_name.getText(), tf_description.getText(), tf_departure.getText(), tf_arrival.getText(), tf_transport.getText(), tf_distance.getText(), tf_duration.getText());

        if (feedbackList.isEmpty()) {
            tourDetailViewModel.updateTour();
            lockInput();
        } else {
            for (String feedback : feedbackList) {
                System.out.println(feedback);
                String existingText = t_feedback.getText();
                t_feedback.setText(existingText + feedback);
            }
        }
    }

    public void exportTourButtonAction(ActionEvent actionEvent) {
        tourDetailViewModel.exportTour();
        t_feedback.setText("Successfully exported.");
    }

    public void deleteTourButtonAction(ActionEvent actionEvent) {
        tourDetailViewModel.deleteTour();
    }

    public void showTourLogsButtonAction(ActionEvent actionEvent) {
        applicationView.mainTabPane.getSelectionModel().select(3);
    }

    private void unlockInput() {
        tf_name.setDisable(false);
        tf_description.setDisable(false);
        tf_departure.setDisable(false);
        tf_arrival.setDisable(false);
        tf_transport.setDisable(false);
        tf_distance.setDisable(false);
        tf_duration.setDisable(false);
    }

    private void lockInput() {
        tf_name.setDisable(true);
        tf_description.setDisable(true);
        tf_departure.setDisable(true);
        tf_arrival.setDisable(true);
        tf_transport.setDisable(true);
        tf_distance.setDisable(true);
        tf_duration.setDisable(true);
    }

    public void importTourButtonAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            tourDetailViewModel.setFile(selectedFile);
            tourDetailViewModel.importTour();

            t_feedback.setText("Successfully imported!");
        } else {
            System.out.println("No file selected");
        }
    }
}
