package com.kongju.backend.sensorLog.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_id", nullable = false)
    private String equipmentId;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "vibration")
    private Double vibration;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // plc가 생성한 시간(timestamp_ms)

    @Column(name = "timestamp_ms", nullable = false)
    private Long timestampMs;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 서버에 기록된 시간

}
