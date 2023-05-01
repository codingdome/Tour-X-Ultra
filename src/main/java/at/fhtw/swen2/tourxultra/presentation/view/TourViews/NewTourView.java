package at.fhtw.swen2.tourxultra.presentation.view.TourViews;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.NewTourViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.service.util.InputValidation;
import at.fhtw.swen2.tourxultra.service.util.InputValidationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class NewTourView implements Initializable {

    private final InputValidation inputValidation = new InputValidationImpl();

    @Autowired
    private NewTourViewModel newTourViewModel;
    @Autowired
    private TourDetailViewModel tourDetailViewModel;

    @FXML
    public TextField tf_name;
    @FXML
    public TextField tf_description;
    @FXML
    public TextField tf_departure;
    @FXML
    public TextField tf_arrival;
    @FXML
    public TextField tf_transport;
    @FXML
    public TextField tf_distance;
    @FXML
    public TextField tf_duration;
    @FXML
    public Label l_feedback;
    @FXML
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        tf_name.textProperty().bindBidirectional(newTourViewModel.nameProperty());
        tf_description.textProperty().bindBidirectional(newTourViewModel.descriptionProperty());
        tf_departure.textProperty().bindBidirectional(newTourViewModel.departureProperty());
        tf_arrival.textProperty().bindBidirectional(newTourViewModel.arrivalProperty());
        tf_transport.textProperty().bindBidirectional(newTourViewModel.transportProperty());
        tf_distance.textProperty().bindBidirectional(newTourViewModel.distanceProperty(), new NumberStringConverter());
        tf_duration.textProperty().bindBidirectional(newTourViewModel.durationProperty(), new NumberStringConverter());
        l_feedback.textProperty().bindBidirectional(newTourViewModel.feedbackProperty());
        tf_distance.textProperty().set("");
        tf_duration.textProperty().set("");
    }

    public void submitButtonAction() {
        l_feedback.setText("");
        List<String> feedbackList = inputValidation.validateNewTourInput(tf_name.getText(), tf_description.getText(), tf_departure.getText(), tf_arrival.getText(), tf_transport.getText(), tf_distance.getText(), tf_duration.getText());
        if (feedbackList.isEmpty()) {
            newTourViewModel.addNewTour();
        } else {
            for (String feedback : feedbackList) {
                System.out.println(feedback);

                String existingText = l_feedback.getText();
                l_feedback.setText(existingText + feedback);
            }
        }
    }

    public void importTourButtonAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            tourDetailViewModel.setFile(selectedFile);
            tourDetailViewModel.importTour();

            l_feedback.setText("Successfully imported!");
        } else {
            System.out.println("No file selected");
        }
    }
}
