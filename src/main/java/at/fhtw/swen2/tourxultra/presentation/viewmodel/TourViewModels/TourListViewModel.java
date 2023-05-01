package at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels;

import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TourListViewModel {

    @Autowired
    TourService tourService;

    private List<Tour> masterData = new ArrayList<>();
    private ObservableList<Tour> tourListItems = FXCollections.observableArrayList();

    public ObservableList<Tour> getTourListItems() {
        return tourListItems;
    }

    public void addItem(Tour tour) {
        tourListItems.add(tour);
        masterData.add(tour);
    }

    public void clearItems() {
        tourListItems.clear();
    }

    public void initList() {
        tourService.getTourList().forEach(this::addItem);
    }

    public void filterList(String searchText) {
        Task<List<Tour>> task = new Task<>() {
            @Override
            protected List<Tour> call() throws Exception {
                updateMessage("Loading data");
                return masterData.stream().filter(value -> value.getName().toLowerCase().contains(searchText.toLowerCase()) || value.getDeparture().toLowerCase().contains(searchText.toLowerCase()) || value.getArrival().toLowerCase().contains(searchText.toLowerCase()) || value.getTransport().toLowerCase().contains(searchText.toLowerCase()) || String.valueOf(value.getDistance()).toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
            }
        };

        task.setOnSucceeded(event -> {
            tourListItems.setAll(task.getValue());
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void updateList(Tour updatedTour) {
        // Find the index of the tour in the masterData list with the same ID as the updated tour
        int index = IntStream.range(0, masterData.size()).filter(i -> masterData.get(i).getId() == updatedTour.getId()).findFirst().orElse(-1);

        // If the tour was found in the masterData list, replace it with the updated tour
        if (index != -1) {
            masterData.set(index, updatedTour);

            // Find the corresponding tour in the tourListItems list and replace it with the updated tour
            Tour tourToUpdate = tourListItems.stream().filter(t -> t.getId() == updatedTour.getId()).findFirst().orElse(null);
            if (tourToUpdate != null) {
                int itemIndex = tourListItems.indexOf(tourToUpdate);
                tourListItems.set(itemIndex, updatedTour);
            }
        }
    }

    public void deleteTour(Tour tourToDelete) {
        // Find the index of the tour in the masterData list with the same ID as the tour to delete
        int index = IntStream.range(0, masterData.size())
                .filter(i -> masterData.get(i).getId() == tourToDelete.getId())
                .findFirst()
                .orElse(-1);

        // If the tour was found in the masterData list, remove it from both lists
        if (index != -1) {
            masterData.remove(index);

            // Find the corresponding tour in the tourListItems list and remove it
            Tour tourToRemove = tourListItems.stream()
                    .filter(t -> t.getId() == tourToDelete.getId())
                    .findFirst()
                    .orElse(null);
            if (tourToRemove != null) {
                tourListItems.remove(tourToRemove);
            }
        }
    }
}
