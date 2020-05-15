package com.space.controller;

import com.space.exception.BadRequestException;
import com.space.exception.NotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class ShipController {

    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ship>> allShips(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String planet,
                                               @RequestParam(required = false) ShipType shipType,
                                               @RequestParam(required = false) Long after,
                                               @RequestParam(required = false) Long before,
                                               @RequestParam(required = false) Boolean isUsed,
                                               @RequestParam(required = false) Double minSpeed,
                                               @RequestParam(required = false) Double maxSpeed,
                                               @RequestParam(required = false) Integer minCrewSize,
                                               @RequestParam(required = false) Integer maxCrewSize,
                                               @RequestParam(required = false) Double minRating,
                                               @RequestParam(required = false) Double maxRating,
                                               @RequestParam(required = false) ShipOrder order,
                                               @RequestParam(required = false) Integer pageNumber,
                                               @RequestParam(required = false) Integer pageSize) {

        List<Ship> ships = shipService.allShips(pageNumber, pageSize, order, name, planet, shipType,
                            after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping(path="/rest/ships/count")
    public Integer count(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String planet,
                         @RequestParam(required = false) ShipType shipType,
                         @RequestParam(required = false) Long after,
                         @RequestParam(required = false) Long before,
                         @RequestParam(required = false) Boolean isUsed,
                         @RequestParam(required = false) Double minSpeed,
                         @RequestParam(required = false) Double maxSpeed,
                         @RequestParam(required = false) Integer minCrewSize,
                         @RequestParam(required = false) Integer maxCrewSize,
                         @RequestParam(required = false) Double minRating,
                         @RequestParam(required = false) Double maxRating) {

               return  shipService.shipsCount(name, planet, shipType, after, before, isUsed,
                       minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> getShip(@PathVariable("id") Long id) throws NotFoundException, BadRequestException {
        if (id==null || id <= 0) throw new BadRequestException();
        Ship ship = shipService.getById(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> add(@RequestBody Ship ship) throws BadRequestException {
        shipService.add(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> updateShip(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String planet,
                                           @RequestParam(required = false) ShipType shipType,
                                           @RequestParam(required = false) Long prodDate,
                                           @RequestParam(required = false) Boolean isUsed,
                                           @RequestParam(required = false) Double speed,
                                           @RequestParam(required = false) Integer crewSize,
                                           @PathVariable("id") Long id)
                                            throws NotFoundException, BadRequestException {
        if (id==null || id <= 0) throw new BadRequestException();
//        if (name==null && planet==null && shipType==null && prodDate==null && isUsed==null &&
//                 speed==null && crewSize==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 //       if (!shipService.edit(name, planet, shipType, prodDate, isUsed, speed, crewSize, id)) return new ResponseEntity<>(HttpStatus.OK);

        Ship ship = shipService.edit(name, planet, shipType, prodDate, isUsed, speed, crewSize, id);

        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ship> deleteShip(@PathVariable("id") Long id) throws NotFoundException, BadRequestException {
        if (id==null || id <= 0) throw new BadRequestException();
        shipService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
