package at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourDetailViewModel;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourListViewModel;
import at.fhtw.swen2.tourxultra.service.LogService;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewLogViewModel {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleIntegerProperty difficulty = new SimpleIntegerProperty();
    private SimpleIntegerProperty duration = new SimpleIntegerProperty();
    private SimpleIntegerProperty rating = new SimpleIntegerProperty();
    private SimpleStringProperty feedback = new SimpleStringProperty();

    private Log log;

    private Tour tour;

    @Autowired
    private LogService logService;

    @Autowired
    TourDetailViewModel tourDetailViewModel;

    @Autowired
    TourListViewModel tourListViewModel;

    @Autowired
    private LogListViewModel logListViewModel;

    public NewLogViewModel() {
    }

    public NewLogViewModel(Log log) {
        this.log = log;
        this.id = new SimpleLongProperty(log.getId());
        this.date = new SimpleStringProperty(log.getDate());
        this.comment = new SimpleStringProperty(log.getComment());
        this.difficulty = new SimpleIntegerProperty(log.getDifficulty());
        this.duration = new SimpleIntegerProperty(log.getDuration());
        this.rating = new SimpleIntegerProperty(log.getRating());
    }

    public void addNewLog() {
        Log log = Log.builder().date(getDate()).comment(getComment()).difficulty(getDifficulty()).duration(getDuration()).rating(getRating()).tour(tour).build();
        System.out.println(log.toString());
        System.out.println(getTour().toString());
        //1 add log
        log = logService.addNew(log);
        if (log != null) {
            //reset all field;
            resetAllTextfieldsAndFeedback();
            //2 update ui in listview
            logListViewModel.addItem(log);
            tourDetailViewModel.setTour(log.getTour());
            tourListViewModel.updateList(log.getTour());
            tourDetailViewModel.setPopularity(log.getTour().getPopularity());
            tourDetailViewModel.setChildFriendliness(log.getTour().getChildFriendliness());
        }
    }

    private void resetAllTextfieldsAndFeedback() {
        setDate("");
        setComment("");
        setDifficulty(0);
        setDuration(0);
        setRating(0);
        setFeedback("Successfully added!");
    }

    // -> getter and setter -> ...

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public SimpleIntegerProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public String getFeedback() {
        return feedback.get();
    }

    public SimpleStringProperty feedbackProperty() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
