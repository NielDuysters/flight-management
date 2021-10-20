package be.kdg.sa.flightmanagement.restcontrollers;

import be.kdg.sa.flightmanagement.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LukasVandenBossche on 20/10/2021
 * @project flightmanagement
 */
@RequestMapping("/api/")
@RestController
public class FlightRestController {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightRestController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PostMapping(value = "/flights")
    public void addFlight(@RequestBody String test) {

        System.out.println("TEST " + test);
    }
}


