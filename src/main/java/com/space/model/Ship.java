package com.space.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Ship {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String planet;
    @Enumerated(value = EnumType.STRING)
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship() {
    }

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

    public Date getProdDate() {
        return prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setProdDate(Date prodDate) {
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

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", planet:'" + planet + '\'' +
                ", shipType:'" + shipType + '\'' +
                ", prodDate:" + prodDate +
                ", isUsed:" + isUsed +
                ", speed:" + speed +
                ", crewSize:" + crewSize +
                ", rating:" + rating +
                '}';
    }

}
