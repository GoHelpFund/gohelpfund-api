package com.gohelpfund.api.v1.fundraiser_service.services;

import com.gohelpfund.api.v1.fundraiser_service.clients.WalletRestTemplateClient;
import com.gohelpfund.api.v1.fundraiser_service.events.source.SimpleSourceBean;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.fundraiser_service.model.wallet.Wallet;
import com.gohelpfund.api.v1.fundraiser_service.repository.FundraiserRepository;
import com.gohelpfund.api.v1.fundraiser_service.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FundraiserService {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserService.class);

    @Autowired
    private FundraiserRepository repository;

/*    @Autowired
    private Tracer tracer;*/

    @Autowired
    WalletRestTemplateClient walletClient;

    @Autowired
    private FundraiserSocialService social;

    @Autowired
    private FundraiserProfessionalService professional;

    @Autowired
    private FundraiserStatusService status;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    private Wallet getWallet(String id) {
        Wallet wallet = walletClient.getWallet(id);

        if (wallet != null) {
            logger.debug("GET | /api/v1/wallets/{id} | found | wallet id: {}", id);
        } else {
            logger.debug("GET | /api/v1/wallets/{id} | not found | wallet id: {}", id);
        }
        return wallet;
    }

    private Wallet createWallet(String fundraiserId, HttpEntity httpEntity) {
        Wallet newWallet = walletClient.createWallet(httpEntity);

        if (newWallet != null) {
            logger.debug("POST | /api/v1/wallets | created | fundraiser id: {} wallet id: {}", fundraiserId, newWallet.getId());
        } else {
            logger.debug("POST | /api/v1/wallets | creation failed | campaign id: {}", fundraiserId);
        }

        return newWallet;
    }

    public List<Fundraiser> getAll() {
        List<Fundraiser> fundraisers = repository.findAll();
        if (fundraisers == null) {
            logger.debug("GET | PostgreSQL | empty | fundraisers size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | fundraisers size: {}", fundraisers.size());
            fundraisers.forEach(fundraiser ->
                    fundraiser.withStatus(status.getStatusByFundraiserId(fundraiser.getFundraiserId()))
                            .withSocial(social.getSocialByFundraiserId(fundraiser.getFundraiserId()))
                            .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.getFundraiserId())));
        }
        return fundraisers;
    }

    public Fundraiser getOne(String fundraiserId) {
//        Span newSpan = tracer.createSpan("getFundraiserDatabaseCall");
        try {
            Fundraiser fundraiser = repository.findByFundraiserId(fundraiserId);

            if (fundraiser == null) {
                logger.debug("GET | PostgreSQL | not found | fundraiser id: {}", fundraiserId);
            } else {
                logger.debug("GET | PostgreSQL | found | fundraiser id: {}", fundraiserId);
                Wallet wallet = getWallet(fundraiser.getWalletId())
                        .withId(fundraiser.getWalletId());

                fundraiser
                        .withStatus(status.getStatusByFundraiserId(fundraiser.getFundraiserId()))
                        .withSocial(social.getSocialByFundraiserId(fundraiser.getFundraiserId()))
                        .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.getFundraiserId()))
                        .withWallet(wallet);
            }

            return fundraiser;
        } finally {
/*            newSpan.tag("peer.service", "postgres");
            newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
            tracer.close(newSpan);*/
        }
    }

    public Fundraiser save() {
        String id = UUID.randomUUID().toString();

        Wallet wallet = createWallet(id, getHttpEntity(id));

        Fundraiser fundraiser = new Fundraiser()
                .withId(id)
                .withWalletId(wallet.getId())
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(id))
                .withProfessional(professional.saveProfessional(id))
                .withWallet(wallet);

        Fundraiser newFundraiser = repository.save(fundraiser);
        logger.debug("POST | PostgreSQL | created | fundraiser id: {} ", newFundraiser.getFundraiserId());

        simpleSourceBean.publishFundraiserChange("SAVE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    public Fundraiser update(Fundraiser fundraiser) {
        Fundraiser newFundraiser = repository.save(fundraiser);

        simpleSourceBean.publishFundraiserChange("UPDATE", newFundraiser.getFundraiserId());
        logger.debug("PUT | PostgreSQL | updated | fundraiser id: {} ", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    private void delete(String id) {
        repository.delete(id);
        logger.debug("DELETE | PostgreSQL | removed | fundraiser id: {} ", id);

        simpleSourceBean.publishFundraiserChange("DELETE", id);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String fundraiserId) {
        HttpHeaders headers = new HttpHeaders();
        String token = UserContextHolder.getContext().getAuthToken();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("entity_id", fundraiserId);
        map.put("type", "fundraiser");

        return new HttpEntity<>(map, headers);
    }

}
