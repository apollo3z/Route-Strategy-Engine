package com.example.aiscollector.infrastracture;

import com.example.aiscollector.persistance.FailedRecordRepository;
import com.example.aiscollector.persistance.model.FailedRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;

@Service
public class FailedRecordService {
    private final Optional<FailedRecordRepository> failedRecordRepository;
    private final Environment environment;
    private static final Logger logger = LogManager.getLogger(FailedRecordService.class);

    public FailedRecordService(Optional<FailedRecordRepository> failedRecordRepository, Environment environment) {
        this.failedRecordRepository = failedRecordRepository;
        this.environment = environment;
    }

    public void handleFailedRecord(String routeId, String errorMessage) {
        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            logger.error("Failed to parse point for routeId: {}, error: {}", routeId, errorMessage);
        } else {
            if (failedRecordRepository.isPresent()) {
                FailedRecord failedRecord = new FailedRecord(null, routeId, errorMessage);
                failedRecordRepository.get().save(failedRecord);
            } else {
                logger.warn("FailedRecordRepository is not available in the current profile.");
            }
        }
    }
}
