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
    @RequestMapping("/api/dashboard/sensor-latest")
    public SensorLogEntity getLatestSensor(){
        SensorLogEntity latestSensor = dashboardService.getLatestSensor();
        return latestSensor;
    }

    @RequestMapping("/api/dashboard/production-chart")
    public List<Object[]> getProductionChart(){
        return dashboardService.getProductionChart();
    }

    @RequestMapping("/api/dashboard/summary")
    public DashboardSummaryResponse getSummary(){
        return dashboardService.getSummary();
    }
}
