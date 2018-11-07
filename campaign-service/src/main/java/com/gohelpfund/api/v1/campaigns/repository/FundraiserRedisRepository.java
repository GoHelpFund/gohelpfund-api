package com.gohelpfund.api.v1.campaigns.repository;

import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;

public interface FundraiserRedisRepository {
    void saveFundraiser(Fundraiser fundraiser);
    void updateFundraiser(Fundraiser fundraiser);
    void deleteFundraiser(String fundraiserId);
    void deleteFundraiser(Fundraiser fundraiser);
    Fundraiser findFundraiser(String fundraiserId);
}
