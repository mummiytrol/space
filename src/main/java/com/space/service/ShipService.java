package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> allShips();
    void add(Ship ship);
    void delete(Ship ship);
    void edit(Ship ship);
    Ship getById(Long id);
    int shipsCount();
}
