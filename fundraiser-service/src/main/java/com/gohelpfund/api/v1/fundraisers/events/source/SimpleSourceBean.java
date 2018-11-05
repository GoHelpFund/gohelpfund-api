package com.gohelpfund.api.v1.fundraisers.events.source;

import com.gohelpfund.api.v1.fundraisers.events.models.FundraiserChangeModel;
import com.gohelpfund.api.v1.fundraisers.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrgChange(String action,String orgId){
       logger.debug("Sending Kafka message {} for Organization Id: {}", action, orgId);
        FundraiserChangeModel change =  new FundraiserChangeModel(
                FundraiserChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
