package be.kdg.sa.flightmanagement.services;

import be.kdg.sa.flightmanagement.repositories.DelayRepository;
import be.kdg.sa.flightmanagement.model.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author LukasVandenBossche on 12/10/2021
 * @project flightmanagement
 */
@Service
public class DelayService {
    private static final Logger log = LoggerFactory.getLogger(DelayService.class);
    private final DelayRepository repository;

    @Autowired
    public DelayService(DelayRepository repository) {
        this.repository = repository;
    }

    public Delay addDelay(Delay delay) {
        try {
            delay.setUUID(UUID.randomUUID());
            return repository.save(delay);
        } catch (Exception e) {
            log.error("Failed to create new delay. " + e.getMessage());
            return null;
        }
    }
}


