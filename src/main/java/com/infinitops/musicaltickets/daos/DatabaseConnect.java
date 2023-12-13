package com.infinitops.musicaltickets.daos;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnect {
    private Connection uconn;


    public DatabaseConnect() {
        if (uconn == null){
            Properties properties = new Properties();
            String server, db, user, password, url;
            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                properties.load(new FileInputStream("C:\\Users\\kim jose\\Documents\\MEGAsync\\ComboTwo\\src\\daos\\conn.properties"));
                server = "localhost:3306/";
                db = "ticketer";
                user = "admin";
                password = "Qwerty!123";
                url = "jdbc:mysql://"+server+db;
                uconn = DriverManager.getConnection(url, user, password);
                System.out.println(uconn);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + e.getMessage());
                System.exit(-1);
            }
        }
    }

    public Connection getUconn() {
        return uconn;
    }


}
