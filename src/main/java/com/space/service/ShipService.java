package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exception.BadRequestException;
import com.space.exception.NotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;

import java.util.Date;
import java.util.List;

public interface ShipService {

    List<Ship> allShips(Integer pageNumber, Integer pageSize, ShipOrder order, String name, String planet,
                        ShipType shipType, Long after, Long before, Boolean getUsed, Double minSpeed,
                        Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                        Double maxRating);

    Ship add(Ship ship) throws BadRequestException;

    void delete(Long id) throws NotFoundException;

    Ship edit(String name, String planet, ShipType type, Date prodDate,
              Boolean isUsed, Double speed, Integer crewSize, Long id)
                throws BadRequestException, NotFoundException;

    Ship getById(Long id) throws NotFoundException;

    int shipsCount(String name, String planet, ShipType shipType, Long after,
                   Long before, Boolean isUsed, Double minSpeed, Double maxSpeed,
                   Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating);
}
