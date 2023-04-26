package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewPersonViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourViewModel;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.TourService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
//@Scope("prototype")
@Slf4j
public class NewTourView implements Initializable {

    private final String ERROR_TOURNAME = "Missing Tourname. ";
    private final String ERROR_DESCRIPTION = "Missing Description. ";
    private final String ERROR_TYPE = "Missing Transport Type. ";
    private final String ERROR_INFORMATION = "Missing Information. ";
    private final String ERROR_ORIGIN = "Missing Start. ";
    private final String ERROR_DESTINATION = "Missing Destination ";
    private final String ERROR_DISTANCE = "Make sure to fill out Distance and only using digits between 0-9.";
    private final String ERROR_TIME = "Make sure to fill out Duration and only using digits between 0-9";


    @Autowired
    private TourService tourService;

    @FXML
    public TextField tf_name;
    @FXML
    public TextField tf_description;
    @FXML
    public ChoiceBox<String> cb_type;
    //    @FXML
//    public TextField tf_type;
    @FXML
    public TextField tf_from;
    @FXML
    public Button submitRouteButton;
    @FXML
    public TextField tf_distance;
    @FXML
    public TextField tf_time;
    @FXML
    public TextField tf_information;
    @FXML
    public TextField tf_to;
    @FXML
    public Label l_feedback;

    @Autowired
    private NewTourViewModel newTourViewModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set up the items in the ChoiceBox
        ObservableList<String> items = FXCollections.observableArrayList("Skateboard", "Mountain bike", "Road bike", "Running", "Hiking", "Roller skates", "Scooter", "Motorcycle", "Car");
        cb_type.setItems(items);

        ObjectProperty<String> selectedType = new SimpleObjectProperty<>();

        cb_type.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedType.set(newValue);
        });

        newTourViewModel.typeProperty().bindBidirectional(selectedType);


        tf_name.textProperty().bindBidirectional(newTourViewModel.nameProperty());
        tf_description.textProperty().bindBidirectional(newTourViewModel.descriptionProperty());

//        tf_type.textProperty().bindBidirectional(newTourViewModel.typeProperty());

        tf_from.textProperty().bindBidirectional(newTourViewModel.originProperty());
        tf_to.textProperty().bindBidirectional(newTourViewModel.toProperty());
        tf_distance.textProperty().bindBidirectional(newTourViewModel.distanceProperty());
        tf_time.textProperty().bindBidirectional(newTourViewModel.timeProperty());
        tf_information.textProperty().bindBidirectional(newTourViewModel.informationProperty());
    }

    public void submitRouteButtonAction(ActionEvent actionEvent) {

        List<String> feedback = inputIsValid();

        if (feedback.size() == 0) {
            newTourViewModel.addNewTour();
            clearInput();
        } else {
            //update Feedback Message
            updateFeedback(feedback);
            System.out.println("ERROR IN INPUT: " + feedback);
        }
    }

    private void clearInput() {
        tf_name.clear();
        tf_description.clear();
        cb_type.setValue("");
        tf_information.clear();
        tf_from.clear();
        tf_to.clear();
        tf_distance.clear();
        tf_time.clear();
        l_feedback.setText("");
    }

    private void updateFeedback(List<String> feedback) {
        String feedbackString = "";
        for (String f : feedback) {
            feedbackString += f;
        }
        l_feedback.setText(feedbackString);
    }

    private List<String> inputIsValid() {
        return Stream.of(
                        (Supplier<String>) () -> !isNotEmpty(tf_name.getText()) ? ERROR_TOURNAME : null,
                        () -> !isNotEmpty(tf_description.getText()) ? ERROR_DESCRIPTION : null,
                        () -> !isNotEmpty((cb_type.getValue())) ? ERROR_TYPE : null,
                        () -> !isNotEmpty(tf_information.getText()) ? ERROR_INFORMATION : null,
                        () -> !isNotEmpty(tf_from.getText()) ? ERROR_ORIGIN : null,
                        () -> !isNotEmpty(tf_to.getText()) ? ERROR_DESTINATION : null,
                        () -> !isNumeric(tf_distance.getText()) ? ERROR_DISTANCE : null,
                        (Supplier<String>) () -> !isNumeric(tf_time.getText()) ? ERROR_TIME : null
                )
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    //for checking the number inputs if there is only numbers
    private static boolean isNumeric(String str) {
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

    //for checking if string is empty
    private static boolean isNotEmpty(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return true;
    }
}
