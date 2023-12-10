package com.infinitops.musicaltickets.daos;

import com.infinitops.musicaltickets.model.Musical;
import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        } catch(Exception e){
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
        } catch(Exception e){
            e.printStackTrace();
        }
        return scheduleList;
    }

}
