package at.fhtw.swen2.tourxultra.presentation.view;

import at.fhtw.swen2.tourxultra.presentation.StageAware;
import at.fhtw.swen2.tourxultra.presentation.events.ApplicationShutdownEvent;
import at.fhtw.swen2.tourxultra.service.impl.ImportExportServiceImpl;
import at.fhtw.swen2.tourxultra.service.io.ImportExportService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
//@Scope("prototype")
@Slf4j
public class ApplicationView implements Initializable, StageAware {


    @FXML
    public TabPane mainTabPane;

    ApplicationEventPublisher publisher;

    ImportExportService importExportService = new ImportExportServiceImpl();

    @FXML
    BorderPane layout;
    @FXML
    MenuItem miPreferences;
    @FXML
    MenuItem miQuit;
    @FXML
    MenuItem miAbout;
    @FXML
    MenuItem miEdit;

    //    Tab mit L neue T und rechts table
    @FXML
    public AnchorPane newTourPane;
    @FXML
    public AnchorPane tourListPane;

    // Tour Details Tab
    @FXML
    public Tab tourDetailsTab;
    @FXML
    public Tab tourLogsTab;
    @FXML
    public Tab logDetailsTab;


    // Toolbar, at some point break out
    @FXML
    Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    public ApplicationView(ApplicationEventPublisher publisher) {
        log.debug("Initializing application controller");
        this.publisher = publisher;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        stage.addListener((obv, o, n) -> n.setTitle(rb.getString("app.title")));
        tbMonitorStatus.setGraphic(monitorStatusIcon);

        newTourPane.setVisible(true);
        tourListPane.setVisible(true);
        tourDetailsTab.setDisable(true);
        tourLogsTab.setDisable(true);
        logDetailsTab.setDisable(true);
    }

    @FXML
    public void onFileClose(ActionEvent event) {
        publisher.publishEvent(new ApplicationShutdownEvent(event.getSource()));
    }

    @FXML
    public void onHelpAbout(ActionEvent event) {
        new AboutDialogView().show();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage.setValue(stage);
    }

    public void importTourMenuButtonAction(ActionEvent actionEvent) {
        importExportService.importFile();
    }
}
