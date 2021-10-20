package be.kdg.sa.flightmanagement.repositories;

import be.kdg.sa.flightmanagement.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author LukasVandenBossche on 29/09/2021
 * @project flightmanagement
 */
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findFlightsByDate(@Param("date") Date date);
}


