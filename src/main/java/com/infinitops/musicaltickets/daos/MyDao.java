package com.infinitops.musicaltickets.daos;

import com.infinitops.musicaltickets.model.Musical;
import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Ticket;
import com.infinitops.musicaltickets.model.Venue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    Statement statement;
    DatabaseConnect connect;

    public MyDao() {
        connect = new DatabaseConnect();
        connection = connect.getUconn();
    }

    public List<Musical> getMusicals(){
        List<Musical> musicalList = new ArrayList<>();
        try{
            String query = "SELECT * FROM musicals";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int _id = 0;
            while (rs.next()){
                Musical musical = new Musical(_id, rs.getString(1), rs.getInt(3), rs.getString(2), rs.getInt(4));
                musicalList.add(musical);
            }
        } catch(NullPointerException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicalList;
    }
    public List<Venue> getVenues(){
        List<Venue> venueList = new ArrayList<>();
        try{
            String query = "SELECT * FROM venues";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int _id = 0;
            while (rs.next()){
                String seats = rs.getString(2);
                Venue v = new Venue(rs.getInt(0), rs.getString(1), seats.split(","));
                venueList.add(v);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return venueList;
    }

    public List<Schedule> getSchedules(){
        List<Schedule> scheduleList = new ArrayList<>();
        try{
            List<Venue> venueList = this.getVenues();
            List<Musical> musicalList = this.getMusicals();
            String query = "SELECT * FROM schedules";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int venueId = rs.getInt(3);
                int musicalId = rs.getInt(2);
                String dateTime = rs.getString(1);
                String[] dateTimeArray = dateTime.split(" ");
                double price = rs.getDouble(4);
                int id  = rs.getInt(0);
                Venue venue = null;
                for (Venue v: venueList) {
                    if(v.get_id() == venueId){
                        venue = v;
                        break;
                    }
                }
                Musical musical = null;
                for (Musical m: musicalList) {
                    if(m.get_id() == musicalId){
                        musical = m;
                        break;
                    }
                }
                Schedule s = new Schedule(venue, dateTimeArray[0], dateTimeArray[1], musical, price);
                scheduleList.add(s);
            }
        } catch(NullPointerException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    public List<Ticket> getTickets(){
        List<Ticket> ticketList = new ArrayList<>();
        try{
            String query = "select id, musical_id, venue_id, type, seat_number, time_slot, price, m.title musical_name, v.name venue_name from tickets t left join musicals m on t.musical_id = m.id left join venues v on t.venue_id = v.id";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setMusicalId(rs.getInt("musical_id"));
                ticket.setMusicalName(rs.getString("musical_name"));
                ticket.setType(rs.getString("type"));
                ticket.setSeatNumber(rs.getString("seat_number"));
                ticket.setTimeSlot(rs.getString("time_slot"));
                ticket.setPrice(rs.getDouble("price"));
                ticket.setVenueId(rs.getInt("venue_id"));
                ticket.setVenueName(rs.getString("venue_name"));
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public boolean insertTicket(Ticket t){
        try {
            preparedStatement = connection.prepareStatement("insert into tickets values(?,?,?,?,?.?)");
            preparedStatement.setInt(0, t.getMusicalId());
            preparedStatement.setInt(1, t.getVenueId());
            preparedStatement.setString(2, t.getType());
            preparedStatement.setString(3, t.getSeatNumber());
            preparedStatement.setDouble(4, t.getPrice());
            return preparedStatement.execute();
        }  catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
