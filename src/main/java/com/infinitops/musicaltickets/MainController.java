package com.infinitops.musicaltickets;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainController {

    @FXML
    private ListView myListView;
    @FXML
    private ListView vBoxPane;


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
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Book Ticket");
            stage.show();
//            Utility.setLogo(box);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exitApp(){
        System.exit(0);
    }
}