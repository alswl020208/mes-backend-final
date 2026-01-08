package com.kongju.backend.sensorLog.repository;

import com.kongju.backend.sensorLog.entiry.SensorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorLogRepository extends JpaRepository<SensorLogEntity, Long> {

     Optional<SensorLogEntity> findTopByOrderByTimestampMsDesc();
}
