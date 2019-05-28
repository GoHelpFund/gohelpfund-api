package com.gohelpfund.api.v1.campaign_service.events.services;

import com.gohelpfund.api.v1.campaign_service.campaigns.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.clients.WalletRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.Wallet;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import com.gohelpfund.api.v1.campaign_service.events.models.EventAttendance;
import com.gohelpfund.api.v1.campaign_service.events.repository.EventAttendanceRepository;
import com.gohelpfund.api.v1.campaign_service.events.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventAttendanceService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private final EventAttendanceRepository repository;

    @Autowired
    public EventAttendanceService( EventAttendanceRepository repository) {
        this.repository = repository;
    }

    public List<EventAttendance> getEventAttendance(String eventId) {
        List<EventAttendance> eventAttendance = repository.findByEventId(eventId);
        if (eventAttendance == null) {
            logger.debug("GET | PostgreSQL | empty | event_attendance size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | event_attendance size: {}", eventAttendance.size());
        }
        return eventAttendance;
    }

    public EventAttendance getEventAttendanceById(String eventAttendanceId) {
        EventAttendance eventAttendance = repository.findByAttendanceId(eventAttendanceId);

        if (eventAttendance == null) {
            logger.debug("GET | PostgreSQL | not found | event_attendance id: {}", eventAttendanceId);
        } else {
            logger.debug("GET | PostgreSQL | found | event_attendance id: {}", eventAttendanceId);
        }
        return eventAttendance;
    }

    public EventAttendance save(String eventId, EventAttendance attendance) {
        String id = UUID.randomUUID().toString();

        attendance.setAttendanceId(id);
        attendance.setEventId(eventId);

        EventAttendance newAttendance = repository.save(attendance);
        logger.debug("POST | PostgreSQL | created | attendance id: {} ", newAttendance.getAttendanceId());
        return attendance;
    }
}
