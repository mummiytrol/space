package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/")
public class ShipController {
 //      private ShipRepo shipRepo;

    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ship>> allShips(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                 @RequestParam(defaultValue = "3", required = false) int pageSize) {
        List<Ship> ships = shipService.allShips(pageNumber, pageSize);
        return new ResponseEntity<>(ships, HttpStatus.OK);
        }

    @GetMapping(path="/rest/ships/count")
    public Integer count() {
               return  shipService.shipsCount();
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> getShip(@PathVariable("id") Long id) {
        Ship ship = shipService.getById(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> add(@RequestBody @Validated Ship ship) {

        shipService.add(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> updateShip(@RequestBody @Validated Ship ship) {
        shipService.edit(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

}
