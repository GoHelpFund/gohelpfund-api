package com.gohelpfund.api.v1.campaign_service.events.services;

import com.gohelpfund.api.v1.campaign_service.campaigns.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.clients.WalletRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.Wallet;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import com.gohelpfund.api.v1.campaign_service.events.models.EventAttendance;
import com.gohelpfund.api.v1.campaign_service.events.repository.EventRepository;
import com.gohelpfund.api.v1.campaign_service.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private final FundraiserRestTemplateClient fundraiserClient;
    private final WalletRestTemplateClient walletClient;
    private final EventRepository repository;
    private final EventAttendanceService attendanceService;


    @Autowired
    public EventService(FundraiserRestTemplateClient fundraiserClient,
                        WalletRestTemplateClient walletClient,
                        EventRepository repository,
                        EventAttendanceService attendanceService) {
        this.fundraiserClient = fundraiserClient;
        this.walletClient = walletClient;
        this.repository = repository;
        this.attendanceService = attendanceService;
    }

    public List<Event> getEvents() {
        List<Event> events = repository.findAll();
        if (events == null) {
            logger.debug("GET | PostgreSQL | empty | events size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | events size: {}", events.size());
            events.forEach(event -> {
                        event.withFundraiser(getFundraiser(event.getFundraiserId()));
                        event.withWallet(getWallet(event.getWalletId()));
                    }
            );
        }
        return events;
    }

    public boolean exists(String eventId) {
        Event event = repository.findByEventId(eventId);

        return event == null;
    }

    public Event getEventById(String eventId) {
        Event event = repository.findByEventId(eventId);

        if (event == null) {
            logger.debug("GET | PostgreSQL | not found | event id: {}", eventId);
        } else {
            logger.debug("GET | PostgreSQL | found | event id: {}", eventId);
            Fundraiser fundraiser = getFundraiser(event.getFundraiserId())
                    .withId(event.getFundraiserId());
            Wallet wallet = getWallet(event.getWalletId())
                    .withId(event.getWalletId());
            List<EventAttendance> attendance = attendanceService.getEventAttendance(eventId);

            event
                    .withFundraiser(fundraiser)
                    .withWallet(wallet)
                    .withAttendance(attendance);
        }
        return event;
    }

    public Wallet updateEvent(String eventId, String walletId, String fundraiserId, Integer amount) {

        String fundraiserName = getFundraiser(fundraiserId).getName();

        Wallet wallet = updateWallet(eventId, walletId, getHttpEntity(fundraiserId, fundraiserName, amount));

        logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", wallet.getId());

        return wallet;
    }

    private Wallet updateWallet(String eventId, String walletId, HttpEntity httpEntity) {
        Wallet newWallet = walletClient.updateWallet(walletId, httpEntity);

        if (newWallet != null) {
            logger.debug("POST | /api/v1/wallets/{}/donate | updated | event id: {} wallet id: {}", walletId, eventId, newWallet.getId());
        } else {
            logger.debug("POST | /api/v1/wallets/{}/donate | update failed | event id: {}", walletId, eventId);
        }

        return newWallet;
    }

    private Fundraiser getFundraiser(String id) {
        return fundraiserClient.getFundraiser(id);
    }

    private Wallet getWallet(String id) {
        Wallet wallet = walletClient.getWallet(id);

        if (wallet != null) {
            logger.debug("GET | /api/v1/wallets/{id} | found | wallet id: {}", id);
        } else {
            logger.debug("GET | /api/v1/wallets/{id} | not found | wallet id: {}", id);
        }
        return wallet;
    }

    private HttpEntity<Map<String, Object>> getHttpEntity(String fundraiserId, String fundraiserName, Integer amount) {
        HttpHeaders headers = new HttpHeaders();
        String token = UserContextHolder.getContext().getAuthToken();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, Object> map = new HashMap<>();
        map.put("entity_id", fundraiserId);
        map.put("entity_name", fundraiserName);
        map.put("amount", amount);
        map.put("type", "fundraiser");

        return new HttpEntity<>(map, headers);
    }
}
