package com.solvd.musicstreamingservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/music_streaming_service_db";
        String user = "root";
        String password = "898980";

        try (Connection connection =
                     DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}