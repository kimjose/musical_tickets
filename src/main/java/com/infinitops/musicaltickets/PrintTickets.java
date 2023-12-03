package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Ticket;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class PrintTickets implements Initializable {

    public GridPane gpTickets;
    private Ticket[] tickets;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
        int i = 0;
        for (Ticket t : tickets) {
//            Document d = new Document();
            try {
                String fname = "ticket_" + i +".txt";
                File f = new File(fname);
                String ticketInfo = getTicketInfo(t);
                FileWriter writer = new FileWriter(fname);
                writer.write("You have booked a ticket. succesfully✅✅✅✅");
                writer.write("\n");
                writer.write(ticketInfo);
                writer.close();

//                UI
                TextArea ta = new TextArea();
                ta.setText(ticketInfo);
                if(i % 2 == 0){
                    gpTickets.add(ta, 0, i/2);
                } else{
                    gpTickets.add(ta, 1, (i-1)/2);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getTicketInfo(Ticket t) {
        String ticketInfo = "Musical: \t" + t.getMusicalName();
        ticketInfo += "\n";
        ticketInfo += "Venue: \t" + t.getVenueName();
        ticketInfo += "\n";
        ticketInfo += "Type: \t" + t.getType();
        ticketInfo += "\n";
        ticketInfo += "Timeslot: \t" + t.getTimeSlot();
        ticketInfo += "\n";
        ticketInfo += "Seat No: \t" + t.getSeatNumber();
        ticketInfo += "\n";
        ticketInfo += "Amount: \t" + t.getPrice();
        ticketInfo += "\n";
        ticketInfo += "----✅---✅---✅---✅---✅---✅---✅---✅---✅---✅---✅---✅---✅";
        return ticketInfo;
    }
}
