package be.kdg.sa.flightmanagement.repositories;

import be.kdg.sa.flightmanagement.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LukasVandenBossche on 29/09/2021
 * @project flightmanagement
 */
public interface AirlineRepository extends JpaRepository<Airline, String> {
}


