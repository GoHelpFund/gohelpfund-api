package com.gohelpfund.api.v1.campaigns.events.handlers;

import com.gohelpfund.api.v1.campaigns.events.CustomChannels;
import com.gohelpfund.api.v1.campaigns.events.models.FundraiserChangeModel;
import com.gohelpfund.api.v1.campaigns.repository.FundraiserRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(CustomChannels.class)
public class FundraiserChangeHandler {

    @Autowired
    private FundraiserRedisRepository fundraiserRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(FundraiserChangeHandler.class);

    @StreamListener("inboundFundraiserChanges")
    public void loggerSink(FundraiserChangeModel fundraiserChange) {
        logger.debug("Received a message of type " + fundraiserChange.getType());
        switch (fundraiserChange.getAction()) {
            case "GET":
                logger.debug("Received a GET event from the fundraiser service for fundraiser id {}", fundraiserChange.getFundraiserId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the fundraiser service for fundraiser id {}", fundraiserChange.getFundraiserId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the fundraiser service for fundraiser id {}", fundraiserChange.getFundraiserId());
                fundraiserRedisRepository.deleteFundraiser(fundraiserChange.getFundraiserId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the fundraiser service for fundraiser id {}", fundraiserChange.getFundraiserId());
                fundraiserRedisRepository.deleteFundraiser(fundraiserChange.getFundraiserId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the fundraiser service of type {}", fundraiserChange.getType());
                break;

        }
    }

}
