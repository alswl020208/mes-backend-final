package com.kongju.backend.plcProductionLog.repository;

import com.kongju.backend.plcProductionLog.entiry.PlcProductionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PlcProductionLogRepository extends JpaRepository<PlcProductionLogEntity, Long> {

    @Query("""
    select 
        function('date_format', p.timestamp, "%Y-%m-%d %H:%i") as time,
        sum(p.count)
    from PlcProductionLogEntity p    
    where p.timestamp >= :start
    group by function('date_format', p.timestamp, "%Y-%m-%d %H:%i")
    order by time
    """)
    List<Object[]> findProductionChart(@Param("start") LocalDateTime start);

    @Query("""
    select sum(p.cumulative)
    from PlcProductionLogEntity p
    where function('date_format', p.timestamp, "%Y-%m-%d") = :today
    """)
    Long findTodayProduction(@Param("today") String today);


}
