package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exception.BadRequestException;
import com.space.model.Ship;

import java.util.List;

public interface ShipService {

    List<Ship> allShips(int pageNumber, int pageSize, ShipOrder order);
    Ship add(Ship ship);
    void delete(Long id);
    void edit(Ship newShip, Long id) throws BadRequestException;
    Ship getById(Long id);
    int shipsCount();
}
