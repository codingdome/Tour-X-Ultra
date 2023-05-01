package at.fhtw.swen2.tourxultra.presentation.view.TourViews;


import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourSearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TourSearchView {

    public static final int PAGE_ITEMS_COUNT = 10;

    @Autowired
    private TourSearchViewModel tourSearchViewModel;

    @FXML
    private TextField tf_search;

    @FXML
    public Button b_search;

    @FXML
    private void initialize() {

        tf_search.textProperty().bindBidirectional(tourSearchViewModel.searchStringProperty());

        b_search.setOnAction(event -> loadData());
        b_search.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        tf_search.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
        });

        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            tf_search.setText(newValue);
        });
    }

    private void loadData() {
        tourSearchViewModel.search();
    }

}
