package be.kdg.sa.flightmanagement.repositories;

import be.kdg.sa.flightmanagement.model.Delay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author LukasVandenBossche on 29/09/2021
 * @project flightmanagement
 */
public interface DelayRepository extends JpaRepository<Delay, UUID> {
}
