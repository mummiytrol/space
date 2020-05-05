package com.space.repository;

import com.space.model.Ship;

import java.util.List;

public interface ShipDAO {
    List<Ship> AllShips();
    void add(Ship ship);
    void delete(Ship ship);
    void edit(Ship ship);
    Ship getById(Long id);
}
