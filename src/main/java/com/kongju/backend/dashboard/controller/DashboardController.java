package com.kongju.backend.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kongju.backend.dashboard.controller.response.DashboardSummaryResponse;
import com.kongju.backend.dashboard.service.DashboardService;
import com.kongju.backend.sensorLog.entiry.SensorLogEntity;


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
        "humidity": 60,
        "timestampMs": 2026,
        "createdAt": "2026-01-08T17:24:30.737"
    }
     */
    @RequestMapping("/api/dashboard/sensor")
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
    @RequestMapping("/api/dashboard/chart")
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

    /*
    제공한 쿼리를 이용해 해당 API를 추가해서 Dashboard에 표시하세요.(결함률은 소숫점 1자리까지 표시하세요.)    
    GET /api/dashboard/defectRate API 추가
    JPQL 사용해서 하기
    */

    @RequestMapping("/api/dashboard/defectRate")
    public double getDefectRate(){
        String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        /*
        defectRate 의 값
        [
           [
                29,
                1
            ]
        ]
        
        결함률 = 결함수 / 전체수 * 100 로 만들어서 결함률 뿌리기
        */ 
        // Number defectRate_per = (Number)defectRate.get(0)[1] / (Number)defectRate.get(0)[0] * 100.0;
        // return defectRate_per;

        List<Object[]> defectRate = dashboardService.getDefectRate(today);

        if (defectRate == null || defectRate.isEmpty()) {return 0.0;}

        Object[] row = defectRate.get(0);

        Number totalCount  = (Number) row[0]; // 전체 수
        Number defectCount = (Number) row[1]; // 결함 수

        if (totalCount.longValue() == 0) { return 0.0;}

        return defectCount.doubleValue() / totalCount.doubleValue() * 100.0;
    }
    
}
