package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserStatus;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FundraiserStatusService {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserProfessionalService.class);

    @Autowired
    private FundraiserStatusRepository repository;

    public List<FundraiserStatus> getStatuses() {
        List<FundraiserStatus> statuses = repository.findAll();

        if (statuses == null) {
            logger.debug("GET | PostgreSQL | empty | fundraisers_status size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | fundraisers_status size: {}", statuses.size());
        }
        return statuses;
    }

    public FundraiserStatus getStatusByFundraiserId(String fundraiserId) {
        FundraiserStatus status = repository.findByFundraiserId(fundraiserId);

        if (status == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_status by fundraiser id: {}", fundraiserId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser_status id: {} fundraiser id: {}", status.getStatusId(), fundraiserId);
        }

        return status;
    }

    public FundraiserStatus getStatusById(String statusId) {
        FundraiserStatus status = repository.findByStatusId(statusId);

        if (status == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_status id: {}", statusId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser id: {} fundraiser_status id: {}", status.getFundraiserId(), statusId);
        }

        return status;
    }

    public FundraiserStatus saveStatus(FundraiserStatus status) {
        status.withId(UUID.randomUUID().toString());
        FundraiserStatus newStatus = repository.save(status);

        logger.debug("POST | PostgreSQL | created | fundraiser_status id: {} ", newStatus.getStatusId());

        return newStatus;
    }

    public FundraiserStatus saveStatus(String fundraiserId) {
        FundraiserStatus newStatus = repository.save(new FundraiserStatus()
                .withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId));
        logger.debug("POST | PostgreSQL | created | fundraiser_status id: {} fundraiser id: {} ", newStatus.getStatusId(), fundraiserId);

        return newStatus;
    }

    public FundraiserStatus updateStatus(FundraiserStatus status) {
        FundraiserStatus newStatus = repository.save(status);
        logger.debug("PUT | PostgreSQL | updated | fundraiser_status id: {} fundraiser id: {} ", newStatus.getStatusId(), newStatus.getFundraiserId());

        return newStatus;
    }

    public void deleteStatus(FundraiserStatus status) {
        repository.delete(status.getFundraiserId());
        logger.debug("DELETE | PostgreSQL | removed | fundraiser_status id: {} fundraiser id: {} ", status.getStatusId(), status.getFundraiserId());

    }

}
