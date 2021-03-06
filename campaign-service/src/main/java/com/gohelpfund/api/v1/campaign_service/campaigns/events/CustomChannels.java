package com.gohelpfund.api.v1.campaign_service.campaigns.events;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
    @Input("inboundFundraiserChanges")
    SubscribableChannel fundraisers();
}
