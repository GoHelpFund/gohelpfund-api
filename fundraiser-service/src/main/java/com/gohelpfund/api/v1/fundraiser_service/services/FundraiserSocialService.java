package com.gohelpfund.api.v1.fundraiser_service.services;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.social.FundraiserSocial;
import com.gohelpfund.api.v1.fundraiser_service.repository.FundraiserSocialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FundraiserSocialService {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserSocialService.class);

    @Autowired
    private FundraiserSocialRepository repository;

    public List<FundraiserSocial> getSocials() {
        List<FundraiserSocial> socials = repository.findAll();
        if (socials == null) {
            logger.debug("GET | PostgreSQL | empty | fundraisers_social size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | fundraisers_social size: {}", socials.size());
        }
        return socials;
    }

    public FundraiserSocial getSocialByFundraiserId(String fundraiserId) {
        FundraiserSocial social =  repository.findByFundraiserId(fundraiserId);

        if (social == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_social by fundraiser id: {}", fundraiserId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser_social id: {} fundraiser id: {}", social.getSocialId(), fundraiserId);
        }

        return social;
    }

    public FundraiserSocial getSocialById(String socialId) {
        FundraiserSocial social =  repository.findBySocialId(socialId);

        if (social == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_social id: {}", socialId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser id: {} fundraiser_social id: {}", social.getFundraiserId(), socialId);
        }

        return social;
    }

    public FundraiserSocial saveSocial(FundraiserSocial social) {
        social
                .withId(UUID.randomUUID().toString());

        FundraiserSocial newSocial = repository.save(social);
        logger.debug("POST | PostgreSQL | created | fundraiser_social id: {} ", newSocial.getSocialId());

        return newSocial;
    }

    public FundraiserSocial saveSocial(String fundraiserId) {
        FundraiserSocial social = new FundraiserSocial()
                .withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId);

        FundraiserSocial newSocial = repository.save(social);
        logger.debug("POST | PostgreSQL | created | fundraiser_social id: {} fundraiser id: {} ", newSocial.getSocialId(), fundraiserId);

        return newSocial;
    }

    public FundraiserSocial updateSocial(FundraiserSocial social) {
        FundraiserSocial newSocial = repository.save(social);
        logger.debug("PUT | PostgreSQL | updated | fundraiser_social id: {} fundraiser id: {} ", newSocial.getSocialId(), newSocial.getFundraiserId());

        return newSocial;

    }

    public void deleteSocial(FundraiserSocial social) {
        repository.delete(social.getFundraiserId());
        logger.debug("DELETE | PostgreSQL | removed | fundraiser_social id: {} fundraiser id: {} ", social.getSocialId(), social.getFundraiserId());
    }

}
