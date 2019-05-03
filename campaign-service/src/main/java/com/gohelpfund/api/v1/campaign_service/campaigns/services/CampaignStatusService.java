package com.gohelpfund.api.v1.campaign_service.campaigns.services;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.status.CampaignStatus;
import com.gohelpfund.api.v1.campaign_service.campaigns.repository.CampaignStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampaignStatusService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignStatusService.class);

    @Autowired
    private CampaignStatusRepository repository;

    public List<CampaignStatus> getAll() {
        List<CampaignStatus> statuses = repository.findAll();

        if (statuses == null) {
            logger.debug("GET | PostgreSQL | not found | campaign_statuses size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | campaign_statuses size: {}", statuses.size());
        }
        return statuses;
    }

    public CampaignStatus geyByCampaignId(String campaignId) {
        CampaignStatus status = repository.findByCampaignId(campaignId);

        if (status == null) {
            logger.debug("GET | PostgreSQL | not found | campaign_status by campaign id: {} ", campaignId);
        } else {
            logger.debug("GET | PostgreSQL | found | campaign_status id: {} by campaign id: {}", status.getStatusId(), status.getCampaignId());
        }
        return status;
    }

    public CampaignStatus getById(String statusId) {
        CampaignStatus status = repository.findByStatusId(statusId);

        if (status == null) {
            logger.debug("GET | PostgreSQL | not found | campaign_status id: {} ", statusId);
        } else {
            logger.debug("GET | PostgreSQL | found | campaign_status id: {} ", status.getStatusId());
        }
        return status;
    }

    public CampaignStatus save(String campaignId) {
        CampaignStatus status = new CampaignStatus()
                .withId(UUID.randomUUID().toString())
                .withCampaignId(campaignId);

        CampaignStatus newStatus = repository.save(status);
        logger.debug("POST | PostgreSQL | saved | campaign_status id: {} ", status.getStatusId());
        return newStatus;
    }

    public CampaignStatus update(CampaignStatus status) {
        CampaignStatus newStatus = repository.save(status);
        logger.debug("PUT | PostgreSQL | updated | campaign_status id: {} ", status.getStatusId());
        return newStatus;
    }

    private void delete(CampaignStatus status) {
        repository.delete(status.getCampaignId());
        logger.debug("DELETE | PostgreSQL | removed | campaign_status id: {} ", status.getStatusId());
    }

}
