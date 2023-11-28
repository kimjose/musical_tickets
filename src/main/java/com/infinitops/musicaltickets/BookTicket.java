package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;
import com.infinitops.musicaltickets.model.Musical;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class BookTicket  implements Initializable {

    public Venue[] venues;
    public ComboBox<String> cbTicketType;
    public ComboBox<Venue> cbVenue;
    public ComboBox<String> cbTimeSlot;
    public ComboBox<String> cbSeatNumber;

    @FXML
    private Label labelMusicalTitle;
    @FXML
    private Label labelMusicalRuntime;
    @FXML
    private Label labelMusicalCategory;
    @FXML
    private Label labelMusicalMinAge;

    private Musical musical;
    private Schedule[] schedules;
    public void setMusical(Musical musical){
        this.musical = musical;
        labelMusicalTitle.setText(musical.getTitle());
        labelMusicalCategory.setText(musical.getCategory());
        labelMusicalRuntime.setText(String.valueOf(musical.getRuntime()));
        labelMusicalMinAge.setText(String.valueOf(musical.getMinAge()));

        String[] ticketTypes = new String[]{"Adult", "Senior"};
        List<String> types = Arrays.asList(ticketTypes);
//        if(musical.getMinAge() < 18) types.add("Student");
        Platform.runLater(() -> {
            cbTicketType.setItems(FXCollections.observableList(types));
        });
    }

    public Musical getMusical(){
        return this.musical;
    }

    public Venue[] getVenues() {
        return venues;
    }

    public void setVenues(Venue[] venues) {
        this.venues = venues;
        cbVenue.setItems(FXCollections.observableList(Arrays.asList(venues)));
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cbVenue.valueProperty().addListener((observableValue, venue, t1) -> {
            List<String> times = new ArrayList<>();
            assert schedules != null;
            for (Schedule s: schedules) {
                if(s.getVenue().equals(t1)) times.add(s.getDate()+ " " + s.getTime());
            }
            cbTimeSlot.setItems(FXCollections.observableList(times));
            cbSeatNumber.setItems(FXCollections.observableList(Arrays.asList(t1.getSeats())));
        });
    }
}
