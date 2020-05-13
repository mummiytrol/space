package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exception.BadRequestException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository repository;

    @Override
    @Transactional
    public List<Ship> allShips(int pageNumber, int pageSize, ShipOrder order) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
        return repository.findAll(page).getContent();
    }

    @Override
    @Transactional
    public Ship add(Ship ship) {
        int year = ship.getProdDate().toInstant().atZone(ZoneId.systemDefault()).getYear();
        double rating = calcRating(ship.getSpeed(), ship.getUsed(), year);
        ship.setRating(rating);
        return repository.save(ship);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void edit(Ship ship, Long id) throws BadRequestException {
        Ship updShip = getById(id);
        String name = ship.getName();
            if (name==null || name.length()>50) throw new BadRequestException();
            if (!name.isEmpty()) updShip.setName(name);

        String planet = ship.getPlanet();
            if (planet==null || planet.length()>50) throw new BadRequestException();
            if (!planet.isEmpty()) updShip.setPlanet(planet);

        ShipType type = ship.getShipType();
            if (type==null) throw new BadRequestException();
            updShip.setShipType(type);

        Date date = ship.getProdDate();
            if (date==null) throw new BadRequestException();
            int year = date.toInstant().atZone(ZoneId.systemDefault()).getYear();
            if (year < 2800 || year > 3019) throw new BadRequestException();
            updShip.setProdDate(date);

        updShip.setUsed(ship.getUsed());
        double speed = updShip.getSpeed();
            speed = (double) Math.round(speed * 100) / 100;
            if (speed < 0.01 || speed > 0.99) throw new BadRequestException();
        updShip.setSpeed(speed);

        int crew = updShip.getCrewSize();
            if (crew < 1 || crew > 9999) throw new BadRequestException();
        updShip.setCrewSize(crew);

        double rating = calcRating(speed, ship.getUsed(), year);
        updShip.setRating(rating);
        repository.saveAndFlush(updShip);
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

    public double calcRating(double speed, boolean isUsed, int year) {
        double k = isUsed ? 0.5d : 1.0d;
        double result = 80 * k * speed / (3020 - year);

        return (double) Math.round(result*100) / 100;
    }

}
