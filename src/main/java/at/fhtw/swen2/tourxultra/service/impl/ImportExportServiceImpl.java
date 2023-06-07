package at.fhtw.swen2.tourxultra.service.impl;

import at.fhtw.swen2.tourxultra.service.dto.SummarizeReport;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.dto.TourReport;
import at.fhtw.swen2.tourxultra.service.io.ImportExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;

@Service
@Transactional
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

            // Create a subfolder for the tours
            File directory = new File("tour_exports");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Write the JSON data to a file in the subfolder
            String filename = tour.getName().replace(" ", "").toLowerCase() + ".json";
            try (FileWriter writer = new FileWriter(directory.getAbsolutePath() + File.separator + filename)) {
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exportTourReport(TourReport tourReport) {
        if (tourReport != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(tourReport);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // Create a subfolder for the tour report
            File directory = new File("tour_reports");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Write the JSON data to a file in the subfolder
            String filename = tourReport.getTour().getName().replace(" ", "").toLowerCase() + "_report" + ".json";
            try (FileWriter writer = new FileWriter(directory.getAbsolutePath() + File.separator + filename)) {
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

    @Override
    public void exportSummarizedReport(SummarizeReport summarizeReport) throws Exception {

        String html = parseThymeleafTemplateSummarizeReport(summarizeReport);
        generatePdfFromHtml(html, summarizeReport.getDate());


        // create the subfolder if it does not exist
        File subfolder = new File("summarized_reports");
        if (!subfolder.exists()) {
            subfolder.mkdir();
        }

        // get the date from the SummarizeReport object
        String date;

        // create a SimpleDateFormat object with the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

        // format the date string using the SimpleDateFormat object
        date = formatter.format(summarizeReport.getDate());

        // construct the filename
        String filename = date + "_summarized_report.json";

        // create the file object
        File file = new File(subfolder, filename);

        // create the Jackson object mapper
        ObjectMapper objectMapper = new ObjectMapper();

        // convert the SummarizeReport object to a JSON string
        try {
            String json = objectMapper.writeValueAsString(summarizeReport);

            // write the JSON string to the file
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseThymeleafTemplateSummarizeReport(SummarizeReport report) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("sumTours", report.getSumTours());
        context.setVariable("date", report.getDate());
        context.setVariable("imagePath", "images/logo_txu.png");


        return templateEngine.process("thymeleaf/summarize_report", context);
    }


    private void generatePdfFromHtml(String html, Date date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = dateFormat.format(date);
        String baseFileName = "Report_" + formattedDate;
        String fileName = baseFileName + ".pdf";
        String subfolderName = "summarized_reports";

        String outputFolderPath = System.getProperty("user.dir") + "/" + subfolderName;
        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();  // Create the subfolder if it doesn't exist
        }

        String outputFilePath = outputFolderPath + "/" + fileName;

        int counter = 1;
        while (fileExists(outputFilePath)) {
            fileName = baseFileName + "_" + counter + ".pdf";
            outputFilePath = outputFolderPath + "/" + fileName;
            counter++;
        }

        OutputStream outputStream = new FileOutputStream(outputFilePath);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    private boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


//    private void generatePdfFromHtml(String html) throws Exception {
//        String outputFolder = "src/main/resources/thymeleaf/Report.pdf";
//        OutputStream outputStream = new FileOutputStream(outputFolder);
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }
}
