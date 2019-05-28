package com.gohelpfund.api.v1.campaign_service.events.repository;

import com.gohelpfund.api.v1.campaign_service.events.models.EventAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventAttendanceRepository extends JpaRepository<EventAttendance, String> {
    EventAttendance findByAttendanceId(String attendanceId);
    List<EventAttendance> findByEventId(String eventId);
}
