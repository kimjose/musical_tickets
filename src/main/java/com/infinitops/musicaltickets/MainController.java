package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Musical;
import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainController {

    private Venue[] venues = new Venue[]{
            new Venue(1, "Lyceum Theatre", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"}),
            new Venue(2, "Royal Drury", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"}),
            new Venue(3, "Nairobi Cinema", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"})
    };
    private Musical[] musicals = new Musical[]{
            new Musical(1, "The Lion King", 130, "Disney Shows, Family and Kids", 6),
            new Musical(2, "Frozen", 145, "Disney Shows, Family and Kids, Weekday Matinees", 6),
    };
    private Schedule[] schedules = new Schedule[]{
            new Schedule(venues[0], "2023-11-30", "18:00", musicals[0]),
            new Schedule(venues[0], "2023-12-02", "18:00", musicals[0]),
            new Schedule(venues[0], "2023-11-29", "14:00", musicals[1]),
            new Schedule(venues[1], "2023-12-01", "17:00", musicals[0]),
            new Schedule(venues[1], "2023-11-30", "18:00", musicals[1])
    };

    @FXML
    private TableView myTableView;
    @FXML
    private VBox vBoxPane;

    @FXML
    protected void showMusicalList(){

    }

    @FXML
    protected void showSchedule(){

    }

    @FXML
    protected void bookTickets(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/infinitops/musicaltickets/book_ticket.fxml")));
            AnchorPane pane = loader.load();
            BookTicket bookTicket = loader.getController();
            bookTicket.setVenues(venues);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Book Ticket");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exitApp(){
        System.exit(0);
    }
}