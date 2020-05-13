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
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/")
public class ShipController {
 //      private ShipRepo shipRepo;

    private ShipService shipService;

//    @PostMapping(path="/add") // Map ONLY POST Requests
//    public @ResponseBody String addNewShip(@RequestParam String name, @RequestParam Long prodDate, @RequestParam Double speed, @RequestParam Integer crewSize ) {
//        Ship ship = new Ship();
//        ship.setName(name);
//        ship.setProdDate(prodDate);
//        ship.setCrewSize(crewSize);
//        ship.setSpeed(speed);
//        return "Saved";
//    }
    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ship>> allShips(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                 @RequestParam(defaultValue = "3", required = false) int pageSize) {
        List<Ship> ships = shipService.allShips();

        return new ResponseEntity<>(ships, HttpStatus.OK);
        }



    @GetMapping(path="/rest/ships/count")
    public Integer count() {
               return  shipService.shipsCount();
    }

//    @RequestMapping(value = "rest/ship", method = RequestMethod.POST)
//    public ModelAndView create(@RequestParam(name = "name") String name, @RequestParam(name = "planet") String planet,
//                       @RequestParam(name = "shipType") String type, @RequestParam(name = "prodDate") Long prodDate,
//                       @RequestParam(name = "isUsed", defaultValue = "false") Boolean isUsed,
//                       @RequestParam(name = "speed") String speed, @RequestParam(name = "crewSize") String crewSize) {
//  //   “name”:[String],   “planet”:[String],   “shipType”:[ShipType],   “prodDate”:[Long],   “isUsed”:[Boolean], --optional, default=false   “speed”:[Double],     “crewSize”:[Integer]
//
//            Ship ship = new Ship();
//            ship.setName(name);
//            Double spd = Double.parseDouble(speed);
//            ship.setSpeed(spd);
//            Integer crew = Integer.parseInt(crewSize);
//            ship.setCrewSize(crew);
//            ship.setUsed(isUsed);
//            ship.setPlanet(planet);
//            for (ShipType shipType : ShipType.values()) {
//                if (shipType.name().equals(type)) ship.setShipType(shipType);
//            }
//            ship.setProdDate(new Date(prodDate));
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        shipService.add(ship);
//
//        return modelAndView;
//    }


//    @GetMapping(path="rest/ships/{id}")
//     public ModelAndView getShip(@PathVariable("id") Long id) {
//        Ship ship = shipService.getById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        modelAndView.addObject("ship", ship);
//      //   shipService.findById(id);
//         return modelAndView;
//    }
    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> getShip(@PathVariable("id") Long id) {
        Ship ship = shipService.getById(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ship", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> addShip(@RequestParam(name = "Name") String name, @RequestParam(name = "Planet") String planet,
                       @RequestParam(name = "shipType") ShipType type, @RequestParam(name = "prodDate") Long prodDate,
                      @RequestParam(name = "isUsed", defaultValue = "false") Boolean isUsed,
                       @RequestParam(name = "speed") Double speed, @RequestParam(name = "crewSize") Integer crewSize) {
                                        // @RequestBody @Validated Ship ship
        HttpHeaders headers = new HttpHeaders();
        Ship ship = new Ship();
            ship.setName(name);
        //    Double spd = Double.parseDouble(speed);
            ship.setSpeed(speed);
         //   Integer crew = Integer.parseInt(crewSize);
            ship.setCrewSize(crewSize);
            ship.setUsed(isUsed);
            ship.setPlanet(planet);
//            for (ShipType shipType : ShipType.values()) {
//                if (shipType.name().equals(type)) ship.setShipType(shipType);
//            }
            ship.setShipType(type);
            ship.setProdDate(new Date(prodDate));
            ship.setRating(1.5);
        shipService.add(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

//    @RequestMapping(value = "rest/ship", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<Ship> updateShip(@RequestBody @Validated Ship ship) {
//        shipService.edit(ship);
//        return new ResponseEntity<>(ship, HttpStatus.OK);
//    }

}
