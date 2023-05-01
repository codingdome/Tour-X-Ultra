package at.fhtw.swen2.tourxultra.presentation.view.LogViews;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.LogDetailViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.LogListViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.NewLogViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.TourPreviewViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourListViewModel;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LogListView implements Initializable {

    @Autowired
    public LogListViewModel logListViewModel;

    @Autowired
    public TourDetailViewModel tourDetailViewModel;

    @Autowired
    public LogDetailViewModel logDetailViewModel;

    @Autowired
    public TourPreviewViewModel tourPreviewViewModel;

    @Autowired
    public NewLogViewModel newLogViewModel;

    @Autowired
    public ApplicationView applicationView;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    public VBox logDataContainer;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        tableView.setItems(logListViewModel.getTourListItems());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn date = new TableColumn("DATE");
        date.setCellValueFactory(new PropertyValueFactory("date"));
        TableColumn comment = new TableColumn("COMMENT");
        comment.setCellValueFactory(new PropertyValueFactory("comment"));
        TableColumn difficulty = new TableColumn("DIFFICULTY");
        difficulty.setCellValueFactory(new PropertyValueFactory("difficulty"));
        TableColumn duration = new TableColumn("DURATION");
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        TableColumn rating = new TableColumn("RATING");
        rating.setCellValueFactory(new PropertyValueFactory("rating"));

        tableView.getColumns().addAll(date, comment, difficulty, duration, rating);

        logDataContainer.getChildren().add(tableView);
        logListViewModel.initList();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Log selectedLog = (Log) tableView.getSelectionModel().getSelectedItem();
                if (selectedLog != null) {
                    System.out.println(selectedLog);
                    setLogDetailsView(selectedLog);
                    newLogViewModel.setLog(selectedLog);
                    newLogViewModel.setTour(selectedLog.getTour());
                    applicationView.logDetailsTab.setDisable(false);
                    applicationView.mainTabPane.getSelectionModel().select(4);
                }
            }
        });
    }

    private void setLogDetailsView(Log log) {
        logDetailViewModel.setLog(log);
        logDetailViewModel.setTour(log.getTour());
        logDetailViewModel.setTour_name(log.getTour().getName());
        logDetailViewModel.setTour_description(log.getTour().getDescription());
        logDetailViewModel.setDate(log.getDate());
        logDetailViewModel.setComment(log.getComment());
        logDetailViewModel.setDifficulty(log.getDifficulty());
        logDetailViewModel.setDuration(log.getDuration());
        logDetailViewModel.setRating(log.getRating());
        logDetailViewModel.setFeedback("");
    }
}
