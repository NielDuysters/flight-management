package be.kdg.sa.flightmanagement.services;

import be.kdg.sa.flightmanagement.repositories.DelayTypeRepository;
import be.kdg.sa.flightmanagement.model.DelayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author LukasVandenBossche on 13/10/2021
 * @project flightmanagement
 */
@Service
public class DelayTypeService {
    private static final Logger log = LoggerFactory.getLogger(DelayTypeService.class);
    private final DelayTypeRepository repository;

    @Autowired
    public DelayTypeService(DelayTypeRepository repository) {
        this.repository = repository;
    }

    public DelayType getDelayType(int id) {
        Optional<DelayType> delayType = repository.findById(id);
        if (delayType.isPresent()) {
            return delayType.get();
        } else {
            log.info("No delay type found with id " + id);
            return null;
        }
    }

    public List<DelayType> getAllDelayTypes() {
        List<DelayType> delayTypes = repository.findAll();
        if (delayTypes.isEmpty()) {
            log.info("No delay types found on");
            return null;
        }
        return delayTypes;
    }
}


