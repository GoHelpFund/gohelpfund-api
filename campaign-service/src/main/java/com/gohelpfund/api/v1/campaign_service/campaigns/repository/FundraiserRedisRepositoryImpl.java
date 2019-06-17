package com.gohelpfund.api.v1.campaign_service.campaigns.repository;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FundraiserRedisRepositoryImpl implements FundraiserRedisRepository {
    private static final String HASH_NAME ="fundraiser";

    private RedisTemplate<String, Fundraiser> redisTemplate;
    private HashOperations hashOperations;

    public FundraiserRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private FundraiserRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void saveFundraiser(Fundraiser newFundraiser) {
        hashOperations.put(HASH_NAME, newFundraiser.getId(), newFundraiser);
    }

    @Override
    public void updateFundraiser(Fundraiser newFundraiser) {
        hashOperations.put(HASH_NAME, newFundraiser.getId(), newFundraiser);
    }

    @Override
    public void deleteFundraiser(String fundraiserId) {
        hashOperations.delete(HASH_NAME, fundraiserId);
    }

    @Override
    public void deleteFundraiser(Fundraiser fundraiser) {
        hashOperations.delete(HASH_NAME, fundraiser.getId());
    }


    @Override
    public Fundraiser findFundraiser(String fundraiserId) {
       return (Fundraiser) hashOperations.get(HASH_NAME, fundraiserId);
    }
}
