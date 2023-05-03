package at.fhtw.swen2.tourxultra.presentation.view;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.NewTourViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.service.SummarizeReportService;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.impl.ImportExportServiceImpl;
import at.fhtw.swen2.tourxultra.service.impl.SummarizeReportServiceImpl;
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
public class WelcomeScreenView implements Initializable {

    @FXML
    public Text t_feedback;

    @Autowired
    ApplicationView applicationView;

    @Autowired
    TourService tourService;

    ImportExportService importExportService = new ImportExportServiceImpl();

    SummarizeReportService summarizeReportService = new SummarizeReportServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle rb) {

    }


    public void getStartedButtonAction(ActionEvent actionEvent) {
        applicationView.mainTabPane.getSelectionModel().select(1);
    }

    public void createSummarizedReportButtonAction(ActionEvent actionEvent) {
        importExportService.exportSummarizedReport(tourService.createSummarizeReport());
        t_feedback.setText("Tour Summarize Report created. You can find it in subfolder: summarized_reports");
    }
}
