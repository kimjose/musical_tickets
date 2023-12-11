package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Ticket;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.*;
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
                String fname = "temp/ticket_" + i +".txt";
                File f = new File(fname);
                String ticketInfo = getTicketInfo(t);
                FileWriter writer = new FileWriter(fname);
                writer.write("You have booked a ticket. succesfully✅✅✅✅");
                writer.write("\n");
                writer.write(ticketInfo);
                writer.close();

                PDDocument pdDocument = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);
                pdDocument.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);

                // Set font and other properties
                contentStream.setFont(PDType1Font.COURIER, 10);
                contentStream.setLeading(15f);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                // Add text to the page
                contentStream.showText("Musical:   " + t.getMusicalName());
                contentStream.newLine();
                contentStream.showText("Venue:     " + t.getVenueName());
                contentStream.newLine();
                contentStream.showText("Type:      " + t.getType());
                contentStream.newLine();
                contentStream.showText("Time Slot: " + t.getTimeSlot());
                contentStream.newLine();
                contentStream.showText("Seat No:   " + t.getSeatNumber());
                contentStream.newLine();
                contentStream.showText("Amount:    " + t.getPrice());
                contentStream.newLine();


                // End the text and content stream
                contentStream.endText();
                contentStream.close();

                // Save the document to a file
                pdDocument.save("temp/ticket_" + i +".pdf");

                // Close the document
                pdDocument.close();


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
