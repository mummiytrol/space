package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository repository;

    @Override
    @Transactional
    public List<Ship> allShips() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void add(Ship ship) {
        repository.save(ship);
    }

    @Override
    @Transactional
    public void delete(Ship ship) {
        repository.delete(ship);
    }

    @Override
    @Transactional
    public void edit(Ship ship) {
        repository.saveAndFlush(ship);
    }

    @Override
    @Transactional
    public Ship getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    @Transactional
    public int shipsCount() {
        return repository.findAll().size();
    }

}
