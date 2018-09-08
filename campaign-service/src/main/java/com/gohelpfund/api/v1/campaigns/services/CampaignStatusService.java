package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.model.CampaignStatus;
import com.gohelpfund.api.v1.campaigns.repository.CampaignStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampaignStatusService {

    @Autowired
    private CampaignStatusRepository repository;

    public List<CampaignStatus> getAll() {
        List<CampaignStatus> statuses = repository.findAll();

        return statuses;
    }

    public CampaignStatus geyByCampaignId(String campaignId) {
        return repository.findByCampaignId(campaignId);
    }

    public CampaignStatus getById(String statusId) {
        return repository.findByStatusId(statusId);
    }

    public CampaignStatus save(CampaignStatus status) {
        status.withId(UUID.randomUUID().toString());
        return repository.save(status);
    }

    public CampaignStatus save(String campaignId) {
        return repository.save(new CampaignStatus().withId(UUID.randomUUID().toString())
                .withCampaignId(campaignId));
    }

    public CampaignStatus update(CampaignStatus status) {
        return repository.save(status);
    }

    public void delete(CampaignStatus status) {
        repository.delete(status.getCampaignId());
    }

}
