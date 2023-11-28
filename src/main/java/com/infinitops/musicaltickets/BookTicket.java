package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;
import com.infinitops.musicaltickets.model.Musical;
import javafx.scene.Label;
import javafx.fxml.Initializable;

public class BookTicket  implements Initializable {

    public Venue[] venues;
    
    @FXML
    private Label labelMusicalTitle;
    @FXML
    private Label labelMusicalRuntime;
    @FXML
    private Label labelMusicalCategory;
    @FXML
    private Label labelMusicalMinAge;

    private Musical musical;

    public void setMusical(Musical musical){
        this.musical = musical;
    }

    public Musical getMusical(){
        return this.musical;
    }

    public Venue[] getVenues() {
        return venues;
    }

    public void setVenues(Venue[] venues) {
        this.venues = venues;
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(musical != null) {
            
        }
    }
}
