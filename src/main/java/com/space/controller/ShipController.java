package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepo;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/")
public class ShipController {
 //       private ShipRepo shipRepo;
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

    @GetMapping(path="/rest/ships")
    public ModelAndView allShips() {
        List<Ship> ships = shipService.allShips();
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<ObjectMapper> shipsList = new ArrayList<>();
//        for (Ship ship : ships) {
//            try {
//                StringWriter writer = new StringWriter();
//                mapper.writeValue(writer, ship);
//                shipsList.add(mapper);
//                System.out.println(ship.getName());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("shipsList", ships);
//        return shipRepo.findAll();
        return modelAndView;
        }



    @GetMapping(path="/rest/ships/count")
    public Integer count() {
               return  shipService.shipsCount();
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam String name, @RequestParam String planet, @RequestParam String type,
                       @RequestParam Long prodDate, @RequestParam Double speed, @RequestParam Integer crewSize) {
  //   “name”:[String],   “planet”:[String],   “shipType”:[ShipType],   “prodDate”:[Long],   “isUsed”:[Boolean], --optional, default=false   “speed”:[Double],     “crewSize”:[Integer]
        Ship ship = new Ship();
        ship.setSpeed(speed);
        ship.setCrewSize(crewSize);
        ship.setName(name);
        ship.setUsed(false);
        ship.setPlanet(planet);
        for (ShipType shipType : ShipType.values()){
            if (shipType.name().equals(type)) ship.setShipType(shipType);
        }
        ship.setProdDate(new Date(prodDate));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/rest");
        shipService.add(ship);
        // return shipRepo.save(ship);
        return modelAndView;
    }

    @GetMapping(path="rest/ships/{id}")
     public ModelAndView getShip(@PathVariable Long id) {
        Ship ship = shipService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("ship", ship);
        // return shipRepo.findById(id);
        return modelAndView;
    }
}
