package com.space.accessingdatamysql;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.model.Ship;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessingDataMysqlApplication {
/*   public static void main(String[] args) {
       // String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Moscow&useSSL=false";
        String url = "jdbc:mysql://localhost:3306/cosmoport?serverTimezone=UTC";
        String username = "root";
        String password = "root";
        System.out.println("Connecting...");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
       ObjectMapper mapper = new ObjectMapper();
       List<ObjectMapper> shipsList = new ArrayList<>();
       for (Ship ship : ships) {
           try {
               StringWriter writer = new StringWriter();
               mapper.writeValue(writer, ship);
               shipsList.add(mapper);
               System.out.println(ship.getName());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    } */
}
