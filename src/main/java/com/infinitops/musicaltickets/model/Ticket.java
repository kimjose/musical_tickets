package com.infinitops.musicaltickets.model;

import com.infinitops.musicaltickets.BookTicket;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class Ticket {
    private String musicalName;
    private int musicalId;
    private String seatNumber;
    private String venueName;
    private int venueId;
    private String type;
    private String timeSlot;
    private double price;
    private BookTicket bookTicket;

    public void setBookTicket(BookTicket bookTicket) {
        this.bookTicket = bookTicket;
    }

    public String getMusicalName() {
        return musicalName;
    }

    public void setMusicalName(String musicalName) {
        this.musicalName = musicalName;
    }

    public int getMusicalId() {
        return musicalId;
    }

    public void setMusicalId(int musicalId) {
        this.musicalId = musicalId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button getRemoveTicketButton(){
        Button btnRemoveTicket = new Button();
        btnRemoveTicket.setText("Remove");
//        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.EXCHANGE);
//        icon.setFill(Paint.valueOf("#19D019"));
//        icon.setSize("12.0");
        btnRemoveTicket.setStyle("-fx-background-color: red;");
//        btnRemoveTicket.setGraphic(icon);
        btnRemoveTicket.setOnAction(event -> {
            if (bookTicket != null) bookTicket.removeTicket(this);
        });
        return btnRemoveTicket;
    }
}
