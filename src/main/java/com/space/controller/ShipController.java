package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(path="/")
public class ShipController {
        private ShipRepo shipRepo;
 //   private ShipService shipService;

//    @PostMapping(path="/add") // Map ONLY POST Requests
//    public @ResponseBody String addNewShip(@RequestParam String name, @RequestParam Long prodDate, @RequestParam Double speed, @RequestParam Integer crewSize ) {
//        Ship ship = new Ship();
//        ship.setName(name);
//        ship.setProdDate(prodDate);
//        ship.setCrewSize(crewSize);
//        ship.setSpeed(speed);
//        return "Saved";
//    }
//    @Autowired
//    public void setShipService(ShipService shipService) {
//        this.shipService = shipService;
//    }

    @GetMapping(path="/rest/ships")
    public Iterable<Ship> allShips() {
//        List<Ship> ships =
//                shipService.allShips();
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
        return shipRepo.findAll();
        }
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ships");
//        modelAndView.addObject("shipsList", ships);


    @GetMapping(path="/ships/count")
    public Long count() {
        return shipRepo.count();
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public Ship create(@RequestParam String name, @RequestParam String planet, @RequestParam String type,
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
        ship.setProdDate(LocalDate.now());
        return shipRepo.save(ship);
    }

    @GetMapping(path="/ships/{id}")
     public Optional<Ship> getShip(@PathVariable Long id) {
        return shipRepo.findById(id);
    }
}
