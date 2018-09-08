package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserStatus;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FundraiserStatusService {

    @Autowired
    private FundraiserStatusRepository repository;

    public List<FundraiserStatus> getStatuses() {
        List<FundraiserStatus> statuses = repository.findAll();

        return statuses;
    }

    public FundraiserStatus getStatusByFundraiserId(String fundraiserId) {
        return repository.findByFundraiserId(fundraiserId);
    }

    public FundraiserStatus getStatusById(String statusId) {
        return repository.findByStatusId(statusId);
    }

    public FundraiserStatus saveStatus(FundraiserStatus status) {
        status.withId(UUID.randomUUID().toString());
        return repository.save(status);
    }

    public FundraiserStatus saveStatus(String fundraiserId) {
        return repository.save(new FundraiserStatus().withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId));
    }

    public FundraiserStatus updateStatus(FundraiserStatus status) {
        return repository.save(status);
    }

    public void deleteStatus(FundraiserStatus status) {
        repository.delete(status.getFundraiserId());
    }

}
