package com.gohelpfund.api.v1.campaign_service.campaigns.events.handlers;

import com.gohelpfund.api.v1.campaign_service.campaigns.events.CustomChannels;
import com.gohelpfund.api.v1.campaign_service.campaigns.events.models.FundraiserChangeModel;
import com.gohelpfund.api.v1.campaign_service.campaigns.repository.FundraiserRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class FundraiserChangeHandler {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserChangeHandler.class);

    private final FundraiserRedisRepository fundraiserRedisRepository;

    @Autowired
    public FundraiserChangeHandler(FundraiserRedisRepository fundraiserRedisRepository) {
        this.fundraiserRedisRepository = fundraiserRedisRepository;
    }

    @StreamListener("inboundFundraiserChanges")
    public void loggerSink(FundraiserChangeModel fundraiserChange) {
        switch (fundraiserChange.getAction()) {
            case "GET":
            case "SAVE":
                logger.debug("SUBSCRIBE | Kafka | {} event | type: {} | fundraiser id: {} ", fundraiserChange.getAction(), fundraiserChange.getType(), fundraiserChange.getFundraiserId());
                break;
            case "UPDATE":
            case "DELETE":
                logger.debug("SUBSCRIBE | Kafka | {} event | type: {} | fundraiser id: {} ", fundraiserChange.getAction(), fundraiserChange.getType(), fundraiserChange.getFundraiserId());
                fundraiserRedisRepository.deleteFundraiser(fundraiserChange.getFundraiserId());
                logger.debug("DELETE | Redis | removed | fundraiser id: {} ", fundraiserChange.getFundraiserId());
                break;
            default:
                logger.debug("SUBSCRIBE | Kafka | UNKNOWN event | type: {} | fundraiser id: {} ", fundraiserChange.getType(), fundraiserChange.getFundraiserId());
                break;

        }
    }

}
