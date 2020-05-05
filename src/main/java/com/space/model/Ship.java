package com.space.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Ship {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String planet;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ShipType shipType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate prodDate;

    private boolean isUsed;

    private double speed;

    private int crewSize;

    private double rating;
/*
    public Ship() {
    }

    public Ship(String name, String planet, String shipType, LocalDate prodDate, boolean isUsed, double speed, int crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;

    } */

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public LocalDate getProdDate() {
        return prodDate;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setProdDate(LocalDate prodDate) {
        this.prodDate = prodDate;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "id:" + id +
//                ", name: '" + name + '\'' +
//                ", planet: '" + planet + '\'' +
//                ", shipType: '" + shipType + '\'' +
//                ", prodDate: " + prodDate +
//                ", isUsed: " + isUsed +
//                ", speed: " + speed +
//                ", crewSize :" + crewSize +
//                ", rating: " + rating +
//                '}';
//    }
    /*  {   “id”:[Long],
       “name”:[String],
          “planet”:[String],
             “shipType”:[ShipType],
                “prodDate”:[Long],   “isUsed”:[Boolean],   “speed”:[Double],     “crewSize”:[Integer],     “rating”:[Double]  },

     */
}
