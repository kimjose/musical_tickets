package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    public AnchorPane apParent;
    public TableView<Ticket> tableTickets;

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
    List<Ticket> tickets = new ArrayList<>();
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
            if(t1 != null) cbSeatNumber.setItems(FXCollections.observableList(Arrays.asList(t1.getSeats())));
        });
        Platform.runLater(() -> {
            tableTickets.prefWidthProperty().bind(apParent.widthProperty());
            tableTickets.getColumns().removeAll(tableTickets.getColumns());
            tableTickets.getColumns().addAll(createColumns(new MyTableColumn[]{
                    new MyTableColumn("Musical ", "musicalName", 0.20),
                    new MyTableColumn("Venue", "venueName", 0.20),
                    new MyTableColumn("Time Slot", "timeSlot", 0.20),
                    new MyTableColumn("Seat Number", "seatNumber", 0.18),
                    new MyTableColumn("Remove Ticket", "removeTicketButton", 0.18),
            }));
        });
    }

    public void bookTicket(){
        Venue v = cbVenue.getValue();
        if (v == null){
            createNotification(4, "Select the venue first");
            return;
        }
        String ticketType = cbTicketType.getValue();
        if(ticketType == null){
            createNotification(4, "Select Ticket type first");
            return;
        }
        String timeSlot = cbTimeSlot.getValue();
        if (timeSlot == null){
            createNotification(4, "Select time slot first");
            return;
        }
        String seatNumber = cbSeatNumber.getValue();
        if(seatNumber == null){
            createNotification(4, "Select seat number");
            return;
        }
        Ticket t = new Ticket();
        t.setMusicalId(musical.get_id());
        t.setMusicalName(musical.getTitle());
        t.setSeatNumber(seatNumber);
        t.setVenueId(v.get_id());
        t.setVenueName(v.getName());
        t.setTimeSlot(timeSlot);
        t.setBookTicket(this);
        tickets.add(t);

        tableTickets.setItems(FXCollections.observableList(tickets));
        cbVenue.getSelectionModel().clearSelection();
        cbTicketType.getSelectionModel().clearSelection();
        cbTimeSlot.getSelectionModel().clearSelection();
        cbSeatNumber.getSelectionModel().clearSelection();
    }

    public void removeTicket(Ticket t){
        tickets.remove(t);
        tableTickets.setItems(FXCollections.observableList(tickets));
    }

    private void createNotification(int type, String message) {
        Notifications n = Notifications.create()
                .owner(apParent)
                .text(message)
                .hideAfter(Duration.seconds(8))
                .darkStyle()
                .position(Pos.BOTTOM_RIGHT);
        Platform.runLater(() -> {
            switch (type) {
                case -1:
                    n.showError();
                    break;
                case 0:
                    n.showInformation();
                    break;
                default:
                    n.showWarning();
            }

        });

    }

    private TableColumn[] createColumns(MyTableColumn[] myTableColumns) {
        TableColumn[] tableColumns = new TableColumn[]{};
        List<TableColumn<String, String>> tableColumnList = new ArrayList<>();
        for (MyTableColumn m : myTableColumns) {
            TableColumn<String, String> tableColumn = new TableColumn<>(m.getName());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(m.getProperty()));
            tableColumn.prefWidthProperty().bind(tableTickets.prefWidthProperty().multiply(m.getMultiplier()));
            tableColumn.setResizable(true);
            tableColumnList.add(tableColumn);
        }
        return tableColumnList.toArray(tableColumns);
    }

}
