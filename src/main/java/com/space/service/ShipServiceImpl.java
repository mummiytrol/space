package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exception.BadRequestException;
import com.space.exception.NotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository repository;

    @Override
    @Transactional
    public List<Ship> allShips(Integer pageNumber, Integer pageSize, ShipOrder order, String name, String planet,
                               ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed,
                               Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                               Double maxRating) {

        if (pageNumber==null) pageNumber = 0;
        if (pageSize==null) pageSize = 3;

        Comparator<Ship> comparator=null;
        if (order==null) {
            order = ShipOrder.ID;
        }
        switch (order) {
            case ID:    comparator = Comparator.comparing(Ship::getId); break;
            case SPEED: comparator = Comparator.comparing(Ship::getSpeed); break;
            case DATE:  comparator = Comparator.comparing(Ship::getProdDate); break;
            case RATING: comparator = Comparator.comparing(Ship::getRating); break;
        }
        List<Ship> newShips = filteredShips(name, planet, shipType, after, before, isUsed,
                                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return newShips.stream().sorted(comparator).skip(pageNumber*pageSize).limit(pageSize).collect(Collectors.toList());
    }

    private List<Ship> filteredShips(String name, String planet, ShipType shipType, Long after,
                                     Long before, Boolean isUsed, Double minSpeed, Double maxSpeed,
                                     Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {

        List<Ship> ships = repository.findAll();
        if (name!=null) {
            ships = ships.stream().filter(ship -> ship.getName().contains(name)).collect(Collectors.toList());
        }
        if (planet!=null) {
            ships = ships.stream().filter(ship -> ship.getPlanet().contains(planet)).collect(Collectors.toList());
        }
        if (shipType != null) {
            ships = ships.stream().filter(ship -> ship.getShipType().equals(shipType)).collect(Collectors.toList());
        }
        if (after != null) {
            ships = ships.stream().filter(ship -> ship.getProdDate().after(new Date(after))).collect(Collectors.toList());
        }
        if (before != null) {
            ships = ships.stream().filter(ship -> ship.getProdDate().before(new Date(before))).collect(Collectors.toList());
        }
        if (isUsed!= null) {
            ships = ships.stream().filter(ship -> ship.getUsed().equals(isUsed)).collect(Collectors.toList());
        }
        if (minSpeed != null) {
            ships = ships.stream().filter(ship -> ship.getSpeed() >= minSpeed).collect(Collectors.toList());
        }
        if (maxSpeed != null) {
            ships = ships.stream().filter(ship -> ship.getSpeed() <= maxSpeed).collect(Collectors.toList());
        }
        if (minCrewSize != null) {
            ships = ships.stream().filter(ship -> ship.getCrewSize() >= minCrewSize).collect(Collectors.toList());
        }
        if (maxCrewSize != null) {
            ships = ships.stream().filter(ship -> ship.getCrewSize() <= maxCrewSize).collect(Collectors.toList());
        }
        if (minRating != null) {
            ships = ships.stream().filter(ship -> ship.getRating() >= minRating).collect(Collectors.toList());
        }
        if (maxRating != null) {
            ships = ships.stream().filter(ship -> ship.getRating() <= maxRating).collect(Collectors.toList());
        }
        return ships;
    }

    @Override
    @Transactional
    public Ship add(Ship ship) throws BadRequestException {
        if (notValidName(ship.getName()) || notValidName(ship.getPlanet()) || notValidDate(ship.getProdDate()) ||
            notValidSpeed(ship.getSpeed()) || (notValidCrew(ship.getCrewSize()) ||
            ship.getName().isEmpty() || ship.getPlanet().isEmpty()) ||  ship.getShipType() == null)
                throw new BadRequestException();
        if (ship.getUsed() == null) ship.setUsed(false);
        int year = ship.getProdDate().toInstant().atZone(ZoneId.systemDefault()).getYear();
        double rating = calcRating(ship.getSpeed(), ship.getUsed(), year);
        ship.setRating(rating);
        return repository.save(ship);
    }

    @Override
    @Transactional
    public void delete(Long id) throws NotFoundException {
        if (!repository.existsById(id)) throw new NotFoundException();
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public Ship edit(String name, String planet, ShipType type, Date date,
                     Boolean isUsed, Double speed, Integer crew, Long id) throws NotFoundException, BadRequestException {

        Ship updShip = getById(id);
       boolean isChange = false;
        // String name = ship.getName();
        if (name!=null) {
            if (name.isEmpty() || name.length() > 50) throw new BadRequestException();
            if (!name.equals(updShip.getName())) {
                isChange = true;
                updShip.setName(name);
                System.out.println("Change Name " + name);
            }
        }
      //  String planet = ship.getPlanet();
        if (planet!=null) {
            if (planet.length() > 50)throw new BadRequestException();
            if (!planet.isEmpty() && !planet.equals(updShip.getPlanet())) {
                isChange = true;
                updShip.setPlanet(planet);
            }
        }
     //   ShipType type = ship.getShipType();
          //  if (type==null) throw new BadRequestException();
        if (type!=null && !type.equals(updShip.getShipType())) {
            isChange = true;
            updShip.setShipType(type);
        }
        if (date != null) {
           // if (prodDate <= 0) throw new BadRequestException();
         //   Date date = new Date(prodDate);
            if (notValidDate(date)) throw new BadRequestException();
            if (!date.equals(updShip.getProdDate())) {
                isChange = true;
                updShip.setProdDate(date);
            }
        }
        if (isUsed!=null && !isUsed.equals(updShip.getUsed())) {
            isChange = true;
            updShip.setUsed(isUsed);
        }
     //   Double speed = updShip.getSpeed();
      //  Double newSpeed = null;
        if (speed!=null) {
             if (notValidSpeed(speed)) throw new BadRequestException();
             speed = (double) Math.round(speed * 100.0d) / 100;
             if (!speed.equals(updShip.getSpeed())) {
                 isChange = true;
                 updShip.setSpeed(speed);
             }
        }
      //  Integer crew = updShip.getCrewSize();
        if (crew!=null) {
            if (notValidCrew(crew)) throw new BadRequestException();
            else
            if (!crew.equals(updShip.getCrewSize())) {
                isChange = true;
                updShip.setCrewSize(crew);
            }
        }
        if (isChange) {
                int year = updShip.getProdDate().toInstant().atZone(ZoneId.systemDefault()).getYear();
                double rating = calcRating(updShip.getSpeed(), updShip.getUsed(), year);
                if (rating != updShip.getRating()) updShip.setRating(rating);
        }

        return updShip;
    }

    @Override
    @Transactional
    public Ship getById(Long id) throws NotFoundException {
        if (!repository.existsById(id)) throw new NotFoundException();
        return repository.findById(id).get();
    }

    @Override
    public int shipsCount(String name, String planet, ShipType shipType, Long after,
                          Long before, Boolean isUsed, Double minSpeed, Double maxSpeed,
                          Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {

        return filteredShips(name, planet, shipType, after, before, isUsed,
                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating).size();
    }

    public double calcRating(double speed, boolean isUsed, int year) {
        double k = isUsed ? 0.5d : 1.0d;
        double result = 80 * k * speed / (3020 - year);
        return (double) Math.round(result*100) / 100;
    }

    private boolean notValidName(String name) {
        return name == null || name.length() > 50;
    }

    private boolean notValidDate(Date date) {
        if (date == null) return true;
        int year = date.toInstant().atZone(ZoneId.systemDefault()).getYear();
        return year < 2800 || year > 3019;
    }

    private boolean notValidSpeed(Double speed) {
        return speed == null || speed < 0.01 || speed > 0.99;
    }

    private boolean notValidCrew(Integer crew) {
        return crew == null || crew < 1 || crew > 9999;
    }
}
