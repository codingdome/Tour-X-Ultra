package at.fhtw.swen2.tourxultra.presentation.view.TourViews;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.LogListViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.NewLogViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.TourPreviewViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourListViewModel;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourListView implements Initializable {

    @Autowired
    public TourListViewModel tourListViewModel;

    @Autowired
    public TourDetailViewModel tourDetailViewModel;

    @Autowired
    public TourPreviewViewModel tourPreviewViewModel;

    @Autowired
    public NewLogViewModel newLogViewModel;

    @Autowired
    public LogListViewModel logListViewModel;

    @Autowired
    public ApplicationView applicationView;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    public VBox tourDataContainer;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        tableView.setItems(tourListViewModel.getTourListItems());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn transport = new TableColumn("TRANSPORT TYPE");
        transport.setCellValueFactory(new PropertyValueFactory("transport"));
        TableColumn departure = new TableColumn("DEPARTURE");
        departure.setCellValueFactory(new PropertyValueFactory("departure"));
        TableColumn arrival = new TableColumn("ARRIVAL");
        arrival.setCellValueFactory(new PropertyValueFactory("arrival"));
        TableColumn distance = new TableColumn("DISTANCE");
        distance.setCellValueFactory(new PropertyValueFactory("distance"));

        tableView.getColumns().addAll(name, transport, departure, arrival, distance);

        tourDataContainer.getChildren().add(tableView);
        tourListViewModel.initList();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Tour selectedTour = (Tour) tableView.getSelectionModel().getSelectedItem();
                if (selectedTour != null) {
                    System.out.println(selectedTour);
                    setTourDetailsView(selectedTour);
                    setTourPreviewView(selectedTour);
                    newLogViewModel.setTour(selectedTour);
                    applicationView.tourDetailsTab.setDisable(false);
                    applicationView.tourLogsTab.setDisable(false);
                    logListViewModel.updateListByTour(selectedTour);
                    applicationView.mainTabPane.getSelectionModel().select(2);
                }
            }
        });
    }

    private void setTourDetailsView(Tour tour) {
        tourDetailViewModel.setTour(tour);
        tourDetailViewModel.setName(tour.getName());
        tourDetailViewModel.setDescription(tour.getDescription());
        tourDetailViewModel.setDeparture(tour.getDeparture());
        tourDetailViewModel.setArrival(tour.getArrival());
        tourDetailViewModel.setTransport(tour.getTransport());
        tourDetailViewModel.setDistance(tour.getDistance());
        tourDetailViewModel.setDuration(tour.getDuration());
        tourDetailViewModel.setImgUrl(tour.getImgUrl());
        tourDetailViewModel.setPopularity(tour.getPopularity());
        tourDetailViewModel.setChildFriendliness(tour.getChildFriendliness());
        tourDetailViewModel.setFeedback("");


    }

    private void setTourPreviewView(Tour tour) {
        tourPreviewViewModel.setName(tour.getName());
        tourPreviewViewModel.setDeparture(tour.getDeparture());
        tourPreviewViewModel.setArrival(tour.getArrival());
    }
}
