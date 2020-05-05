package com.space.service;

import com.space.repository.ShipDAO;
import com.space.model.Ship;
import com.space.repository.ShipDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    private ShipDAO shipDAO = new ShipDAOImpl();

//    public void setShipDAO(ShipDAO shipDAO) {
//        this.shipDAO = shipDAO;
//    }

    @Override
    @Transactional
    public List<Ship> allShips() {
        return shipDAO.AllShips();
    }

    @Override
    @Transactional
    public void add(Ship ship) {
        shipDAO.add(ship);
    }

    @Override
    @Transactional
    public void delete(Ship ship) {
        shipDAO.delete(ship);
    }

    @Override
    @Transactional
    public void edit(Ship ship) {
        shipDAO.edit(ship);
    }

    @Override
    @Transactional
    public Ship getById(Long id) {
        return shipDAO.getById(id);
    }

    @Override
    public int shipsCount() {
        return shipDAO.AllShips().size();
    }
}
