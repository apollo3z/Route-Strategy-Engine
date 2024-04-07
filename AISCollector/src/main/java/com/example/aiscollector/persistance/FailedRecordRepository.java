package com.example.aiscollector.persistance;

import com.example.aiscollector.persistance.model.FailedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedRecordRepository extends JpaRepository<FailedRecord, Long> {
}