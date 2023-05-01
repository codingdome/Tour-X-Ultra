package at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels;

import at.fhtw.swen2.tourxultra.service.LogService;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class LogListViewModel {

    @Autowired
    LogService logService;

    private List<Log> masterData = new ArrayList<>();
    private ObservableList<Log> logListItems = FXCollections.observableArrayList();

    public ObservableList<Log> getTourListItems() {
        return logListItems;
    }

    public void addItem(Log log) {
        logListItems.add(log);
        masterData.add(log);
    }

    public void clearItems() {
        logListItems.clear();
    }

    public void initList() {
        logService.getLogList().forEach(this::addItem);
    }


    public void filterList(String searchText) {
        Task<List<Log>> task = new Task<>() {
            @Override
            protected List<Log> call() throws Exception {
                updateMessage("Loading data");
                return masterData.stream().filter(log -> log.getDate().toLowerCase().contains(searchText.toLowerCase()) || log.getComment().toLowerCase().contains(searchText.toLowerCase()) || String.valueOf(log.getDifficulty()).toLowerCase().contains(searchText.toLowerCase()) || String.valueOf(log.getDuration()).toLowerCase().contains(searchText.toLowerCase()) || String.valueOf(log.getRating()).toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
            }
        };

        task.setOnSucceeded(event -> {
            logListItems.setAll(task.getValue());
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void updateList(Log updatetLog) {
        // Find the index of the tour in the masterData list with the same ID as the updated tour
        int index = IntStream.range(0, masterData.size()).filter(i -> masterData.get(i).getId() == updatetLog.getId()).findFirst().orElse(-1);

        // If the tour was found in the masterData list, replace it with the updated tour
        if (index != -1) {
            masterData.set(index, updatetLog);

            // Find the corresponding tour in the tourListItems list and replace it with the updated tour
            Log logToUpdate = logListItems.stream().filter(t -> t.getId() == updatetLog.getId()).findFirst().orElse(null);
            if (logToUpdate != null) {
                int itemIndex = logListItems.indexOf(logToUpdate);
                logListItems.set(itemIndex, updatetLog);
            }
        }
    }

    public void updateListByTour(Tour tour) {
        masterData.clear();
        logListItems.clear();
        logService.getLogListByTour(tour).forEach(this::addItem);
    }

    public void deleteLog(Log logToDelete) {
        // Find the index of the tour in the masterData list with the same ID as the tour to delete
        int index = IntStream.range(0, masterData.size()).filter(i -> masterData.get(i).getId() == logToDelete.getId()).findFirst().orElse(-1);

        // If the tour was found in the masterData list, remove it from both lists
        if (index != -1) {
            masterData.remove(index);

            // Find the corresponding tour in the tourListItems list and remove it
            Log logToRemove = logListItems.stream().filter(t -> t.getId() == logToDelete.getId()).findFirst().orElse(null);
            if (logToRemove != null) {
                logListItems.remove(logToRemove);
            }
        }
    }


}
