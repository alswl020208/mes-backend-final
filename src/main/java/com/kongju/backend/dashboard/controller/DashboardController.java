package com.kongju.backend.dashboard.controller;

import com.kongju.backend.dashboard.controller.response.DashboardSummaryResponse;
import com.kongju.backend.dashboard.service.DashboardService;
import com.kongju.backend.plcProductionLog.repository.PlcProductionLogRepository;
import com.kongju.backend.sensorLog.entiry.SensorLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {


    /**
     *  요약정보 조회(summary)
     *  센서 최신 정보 조회(sensor-latest)
     *  프로덕션 차트 정보 조회(production-chart)
     */

    @Autowired
    private DashboardService dashboardService;

    /*
    # 값이 없는 경우
    '공백'

    # 값이 있는 경우
    {
        "id": 1,
        "equipmentId": "EQ-001",
        "temperature": 40,
        "pressure": 52,
        "vibration": 52,
        "speed": 32,
        "timestamp": "2026-01-08T17:24:30",
        "timestampMs": 2026,
        "createdAt": "2026-01-08T17:24:30.737"
    }
     */
    @RequestMapping("/api/dashboard/sensor-latest")
    public SensorLogEntity getLatestSensor(){
        SensorLogEntity latestSensor = dashboardService.getLatestSensor();
        return latestSensor;
    }

    /*
    응답 예제
    # 값이 없는 경우
    []

    # 값이 있는 경우
    [
        ["2026-01-08 17:20",11],
        ["2026-01-08 17:21",32],
        ["2026-01-08 17:22",125],
        ["2026-01-08 17:23",4],
        ["2026-01-08 17:24",10],
        ["2026-01-08 17:25",3],
        ["2026-01-08 17:26",50],
    ]
     */
    @RequestMapping("/api/dashboard/production-chart")
    public List<Object[]> getProductionChart(){
        return dashboardService.getProductionChart();
    }

    /*
    # 값이 없는 경우
    {
        "equipmentStatus": "RUN",
        "todayProduction": 0,
        "activeAlarmCount": 3,
        "defectRate": 13.7
    }

    # 값이 있는 경우
    {
        "equipmentStatus": "RUN",
        "todayProduction": 3,
        "activeAlarmCount": 3,
        "defectRate": 13.7
    }
     */
    @RequestMapping("/api/dashboard/summary")
    public DashboardSummaryResponse getSummary(){
        return dashboardService.getSummary();
    }
}
