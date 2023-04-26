package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.PersonListViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
public class TourListView implements Initializable {

    @Autowired
    public TourListViewModel tourListViewModel;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        tableView.setItems(tourListViewModel.getTourListItems());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn description = new TableColumn("DESCRIPTION");
        description.setCellValueFactory(new PropertyValueFactory("description"));
        TableColumn origin = new TableColumn("FROM");
        origin.setCellValueFactory(new PropertyValueFactory("origin"));
        TableColumn to = new TableColumn("TO");
        to.setCellValueFactory(new PropertyValueFactory("to"));

        tableView.getColumns().addAll(name, description, origin, to);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Tour selectedTour = (Tour) tableView.getSelectionModel().getSelectedItem();
                if (selectedTour != null) {
                    System.out.println(selectedTour.getName() + " " + selectedTour.getDescription() + " " + selectedTour.getOrigin() + " " + selectedTour.getTo() + " " + selectedTour.getInformation());
                }
            }
        });

        dataContainer.getChildren().add(tableView);
        tourListViewModel.initList();
    }

}
