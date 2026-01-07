package com.kongju.backend.sensorLog.repository;

import com.kongju.backend.sensorLog.entiry.SensorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorLogRepository extends JpaRepository<SensorLogEntity, Long> {

     SensorLogEntity findTopByOrderByTimestampMsDesc();
}
