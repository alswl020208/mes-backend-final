package com.kongju.backend.dashboard.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongju.backend.dashboard.controller.response.DashboardSummaryResponse;
import com.kongju.backend.plcProductionLog.repository.PlcProductionLogRepository;
import com.kongju.backend.sensorLog.entiry.SensorLogEntity;
import com.kongju.backend.sensorLog.repository.SensorLogRepository;

@Service
public class DashboardService {

    @Autowired
    private SensorLogRepository sensorLogRepository;

    public SensorLogEntity getLatestSensor(){
        Optional<SensorLogEntity> sensorLogEntity = sensorLogRepository.findTopByOrderByTimestampMsDesc();
        return sensorLogEntity.orElse(null);
    }

    @Autowired
    private PlcProductionLogRepository plcProductionLogRepository ;

    public List<Object[]> getProductionChart(){
        LocalDateTime nowMinus3Hours = LocalDateTime.now().minusHours(3);
        List<Object[]> queryResult = plcProductionLogRepository.findProductionChart(nowMinus3Hours);
        return queryResult;
    }

    public DashboardSummaryResponse getSummary(){
    /*
    private String equipmentStatus;   // RUN / STOP / ALARM  //장비상태
    private long todayProduction;   // 오늘의 생산량
    private long activeAlarmCount;  // 알람 카운트
    private double defectRate;      // 불량률
    */
        String equipmentStatus = "RUN";
        long activeAlarmCount = 3;
        String today = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Optional<Long> todayProduction = plcProductionLogRepository.findTodayProduction(today);
        double defectRate = 13.7;

        return new DashboardSummaryResponse(equipmentStatus, todayProduction.orElse(0L), activeAlarmCount, defectRate);
    }

    public List<Object[]> getDefectRate(String today){
        List<Object[]> defectRate = plcProductionLogRepository.findTodayDefectRate(today);
        return defectRate;
    }
    

}
