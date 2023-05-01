package at.fhtw.swen2.tourxultra.presentation.view;

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
public class WelcomeScreenView implements Initializable {


    @Autowired
    ApplicationView applicationView;


    @Override
    public void initialize(URL location, ResourceBundle rb) {

    }


    public void getStartedButtonAction(ActionEvent actionEvent) {
        applicationView.mainTabPane.getSelectionModel().select(1);
    }
}
