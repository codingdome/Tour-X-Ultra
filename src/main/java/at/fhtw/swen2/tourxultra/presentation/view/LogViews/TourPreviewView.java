package at.fhtw.swen2.tourxultra.presentation.view.LogViews;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.NewLogViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.TourPreviewViewModel;
import at.fhtw.swen2.tourxultra.service.util.InputValidation;
import at.fhtw.swen2.tourxultra.service.util.InputValidationImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class TourPreviewView implements Initializable {

    @Autowired
    private TourPreviewViewModel tourPreviewViewModel;

    @FXML
    public Text t_name;
    @FXML
    public Text t_departure;
    @FXML
    public Text t_arrival;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        t_name.textProperty().bindBidirectional(tourPreviewViewModel.nameProperty());
        t_departure.textProperty().bindBidirectional(tourPreviewViewModel.departureProperty());
        t_arrival.textProperty().bindBidirectional(tourPreviewViewModel.arrivalProperty());
    }

}
