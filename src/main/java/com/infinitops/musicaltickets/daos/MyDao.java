package com.infinitops.musicaltickets.daos;

import com.infinitops.musicaltickets.model.Musical;

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
}
