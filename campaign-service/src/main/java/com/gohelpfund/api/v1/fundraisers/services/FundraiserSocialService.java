package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserSocial;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FundraiserSocialService {

    @Autowired
    private FundraiserSocialRepository repository;

    public List<FundraiserSocial> getSocials() {
        List<FundraiserSocial> socials = repository.findAll();

        return socials;
    }

    public FundraiserSocial getSocialByFundraiserId(String fundraiserId) {
        return repository.findByFundraiserId(fundraiserId);
    }

    public FundraiserSocial getSocialById(String socialId) {
        return repository.findBySocialId(socialId);
    }

    public FundraiserSocial saveSocial(FundraiserSocial social) {
        social.withId(UUID.randomUUID().toString());
        return repository.save(social);
    }

    public FundraiserSocial saveSocial(String fundraiserId) {
        return repository.save(new FundraiserSocial().withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId));
    }

    public FundraiserSocial updateSocial(FundraiserSocial social) {
        return repository.save(social);
    }

    public void deleteSocial(FundraiserSocial social) {
        repository.delete(social.getFundraiserId());
    }

}
