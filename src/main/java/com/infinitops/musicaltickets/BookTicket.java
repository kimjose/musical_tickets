package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.*;

public class BookTicket  implements Initializable {

    public Venue[] venues;
    public ComboBox<String> cbTicketType;
    public ComboBox<Venue> cbVenue;
    public ComboBox<Schedule> cbTimeSlot;
    public ComboBox<String> cbSeatNumber;
    public AnchorPane apParent;
    public TableView<Ticket> tableTickets;
    public Button btnPrintTickets;
    public Label labelTotal;

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
        labelMusicalRuntime.setText(musical.getRuntimeString());
        labelMusicalMinAge.setText(String.valueOf(musical.getMinAge()));

        String[] ticketTypes = new String[]{"Adult", "Senior", "Student"};
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
            List<Schedule> scheduleList = new ArrayList<>();
            assert schedules != null;
            for (Schedule s: schedules) {
                if(s.getVenue().equals(t1)) scheduleList.add(s);
            }
            cbTimeSlot.setItems(FXCollections.observableList(scheduleList));
            if(t1 != null) cbSeatNumber.setItems(FXCollections.observableList(Arrays.asList(t1.getSeats())));
        });
        Platform.runLater(() -> {
            tableTickets.prefWidthProperty().bind(apParent.widthProperty());
            tableTickets.getColumns().removeAll(tableTickets.getColumns());
            tableTickets.getColumns().addAll(createColumns(new MyTableColumn[]{
                    new MyTableColumn("Musical ", "musicalName", 0.15),
                    new MyTableColumn("Venue", "venueName", 0.14),
                    new MyTableColumn("Ticket type", "type", 0.13),
                    new MyTableColumn("Time Slot", "timeSlot", 0.18),
                    new MyTableColumn("Seat Number", "seatNumber", 0.1),
                    new MyTableColumn("Price", "price", 0.10),
                    new MyTableColumn("Remove Ticket", "removeTicketButton", 0.18),
            }));
            btnPrintTickets.setDisable(true);
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
        Schedule schedule = cbTimeSlot.getValue();
        if (schedule == null){
            createNotification(4, "Select time slot first");
            return;
        }
        String seatNumber = cbSeatNumber.getValue();
        if(seatNumber == null){
            createNotification(4, "Select seat number");
            return;
        }
        boolean seatBooked = false;
        for(Ticket t: tickets){
            if(t.getSeatNumber() == seatNumber && schedule.toString().equals(t.getTimeSlot())) seatBooked = true;
        }
        if (seatBooked){
            createNotification(-1, "This seat has already been booked");
            return;
        }
        Ticket t = new Ticket();
        t.setMusicalId(musical.get_id());
        t.setMusicalName(musical.getTitle());
        t.setType(ticketType);
        t.setSeatNumber(seatNumber);
        t.setVenueId(v.get_id());
        t.setVenueName(v.getName());
        t.setTimeSlot(schedule.toString());
        t.setPrice(schedule.getPrice());
        t.setBookTicket(this);
        tickets.add(t);

        tableTickets.setItems(FXCollections.observableList(tickets));
        updateCost();
        cbVenue.getSelectionModel().clearSelection();
        cbTicketType.getSelectionModel().clearSelection();
        cbTimeSlot.getSelectionModel().clearSelection();
        cbSeatNumber.getSelectionModel().clearSelection();
    }

    public void removeTicket(Ticket t){
        tickets.remove(t);
        tableTickets.setItems(FXCollections.observableList(tickets));
        updateCost();
    }

    private void updateCost(){
        double cost = 0;
        for (Ticket t: tickets) {
            cost = cost + t.getPrice();
        }
        labelTotal.setText(Double.toString(cost));
        if(cost == 0) btnPrintTickets.setDisable(true);
        else btnPrintTickets.setDisable(false);
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

    public void printTickets(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/infinitops/musicaltickets/print_tickets.fxml")));
            AnchorPane pane = loader.load();
            PrintTickets printTickets = loader.getController();
            printTickets.setTickets(tickets.toArray(new Ticket[]{}));
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
}
