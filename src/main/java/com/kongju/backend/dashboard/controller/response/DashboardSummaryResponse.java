package com.kongju.backend.dashboard.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryResponse {
    private String equipmentStatus;   // RUN / STOP / ALARM
    private long todayProduction;
    private long activeAlarmCount;
    private double defectRate;
}
