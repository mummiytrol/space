package com.space.accessingdatamysql;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.config.AppConfig;
import com.space.model.Ship;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessingDataMysqlApplication {
 /*  public static void main(String[] args) {
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
//       ObjectMapper mapper = new ObjectMapper();
//       List<ObjectMapper> ships = new ArrayList<>();
//       for (Ship ship : ships) {
//           try {
//               StringWriter writer = new StringWriter();
//               mapper.writeValue(writer, ship);
//               ships.add(mapper);
//               System.out.println(ship.getName());
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//       }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
       EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
       try {
           nativeQuery(emf, "SHOW TABLES");
           nativeQuery(emf, "SHOW COLUMNS from ship");
           Ship ship = new Ship();
            ship.setName("sokol");
            ship.setPlanet("mars");
           persistEntity(emf, ship);
           nativeQuery(emf, "Select * from ship");
       } finally {
           emf.close();
       }
   }

   private static void persistEntity(EntityManagerFactory emf, Ship ship) {
       System.out.println("------------");      System.out.println("Persisting person: " + ship);
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(ship);
       em.getTransaction().commit();
       em.close();

   }  private static void nativeQuery(EntityManagerFactory emf, String s) {
       EntityManager em = emf.createEntityManager();
       System.out.printf("---------------------------%n'%s'%n", s);
       Query query = em.createNativeQuery(s);
       List list = query.getResultList();
       for (Object o : list) {
           if (o instanceof Object[]) {
               System.out.println(Arrays.toString((Object[]) o));
           } else {
               System.out.println(o);
        }
        }
        em.close();
   } */
}

