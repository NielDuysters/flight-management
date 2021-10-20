package be.kdg.sa.flightmanagement.services;

import be.kdg.sa.flightmanagement.repositories.FlightRepository;
import be.kdg.sa.flightmanagement.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author LukasVandenBossche on 29/09/2021
 * @project flightmanagement
 */
@Service
public class FlightService {
    private static final Logger log = LoggerFactory.getLogger(FlightService.class);
    private final FlightRepository repository;

    @Autowired
    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public Flight getFlight(UUID uuid) {
        Optional<Flight> flight = repository.findById(uuid);
        if (flight.isPresent()) {
            return flight.get();
        } else {
            log.info("No flight found with uuid: " + uuid);
            return null;
        }
    }

    public Flight changeFlight(Flight flight) {
        try {
            Flight updatedFlight = repository.save(flight);
            return updatedFlight;
        } catch (Exception e) {
            log.error("Failed to update flight. " + e.getMessage());
            return null;
        }
    }

    public List<Flight> getAllFlightsOnDate(Date date) {
        List<Flight> flights = repository.findFlightsByDate(date);
        if (flights.isEmpty()) {
            log.info("No flights found on " + date);
            return null;
        }
        return flights;
    }
}


