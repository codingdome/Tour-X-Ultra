package at.fhtw.swen2.tourxultra.service.impl;

import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.io.ImportExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImportExportServiceImpl implements ImportExportService {
    @Override
    public void exportTour(Tour tour) {
        if (tour != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            //parse object to json string
            try {
                json = objectMapper.writeValueAsString(tour);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try (FileWriter writer = new FileWriter(tour.getId() + ".json")) {
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public File importFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null); // Show the file chooser dialog
        if (result == JFileChooser.APPROVE_OPTION) { // User clicked the "Open" button
            File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
            return selectedFile;
        } else { // User clicked the "Cancel" button or closed the dialog
            return null;
        }
    }

    @Override
    public Tour importTour() {
        return null;
    }
}
