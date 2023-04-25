package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewPersonViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourViewModel;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.TourService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
//@Scope("prototype")
@Slf4j
public class NewTourView implements Initializable {

    @Autowired
    private TourService tourService;

    @FXML
    public TextField tf_name;
    @FXML
    public TextField tf_description;
    @FXML
    public TextField tf_type;
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

    @Autowired
    private NewTourViewModel newTourViewModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_name.textProperty().bindBidirectional(newTourViewModel.nameProperty());
        tf_description.textProperty().bindBidirectional(newTourViewModel.descriptionProperty());
        tf_type.textProperty().bindBidirectional(newTourViewModel.typeProperty());
        tf_from.textProperty().bindBidirectional(newTourViewModel.originProperty());
        tf_to.textProperty().bindBidirectional(newTourViewModel.toProperty());
        tf_distance.textProperty().bindBidirectional(newTourViewModel.distanceProperty());
        tf_time.textProperty().bindBidirectional(newTourViewModel.timeProperty());
        tf_information.textProperty().bindBidirectional(newTourViewModel.informationProperty());
    }

    public void submitRouteButtonAction(ActionEvent actionEvent) {
        System.out.println("name: " + tf_name.getText());
        System.out.println("description: " + tf_description.getText());
        System.out.println("type: " + tf_type.getText());
        System.out.println("from: " + tf_from.getText());
        System.out.println("to: " + tf_to.getText());
        System.out.println("distance: " + tf_distance.getText());
        System.out.println("time: " + tf_time.getText());
        System.out.println("information: " + tf_information.getText());

        newTourViewModel.addNewTour();
    }
}
