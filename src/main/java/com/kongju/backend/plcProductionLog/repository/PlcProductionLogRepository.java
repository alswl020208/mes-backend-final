package com.kongju.backend.plcProductionLog.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kongju.backend.plcProductionLog.entiry.PlcProductionLogEntity;

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
    select sum(p.count)
    from PlcProductionLogEntity p
    where function('date_format', p.timestamp, "%Y-%m-%d") = :today
    """)
    Optional<Long> findTodayProduction(@Param("today") String today);


    /*
    실제 sql Query
    SELECT COUNT(*) as total_count, SUM( CASE WHEN is_defect = 1 THEN 1 ELSE 0 END ) as defect_count
    FROM plc_production_log
    WHERE date_format(timestamp, '%Y-%m-%d') = '2026-01-13'
    */
    @Query("""
    select 
        count(p) as totalCount,
        sum( case when p.isDefect = true then 1 else 0 end ) as defectCount
    from PlcProductionLogEntity p
    where function('date_format', p.timestamp, "%Y-%m-%d") = :today
    """)
    List<Object[]> findTodayDefectRate(@Param("today") String today);


}
