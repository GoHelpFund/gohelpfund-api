package com.gohelpfund.api.v1.campaign_service.events.repository;

import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    Event findByEventId(String eventId);

}
