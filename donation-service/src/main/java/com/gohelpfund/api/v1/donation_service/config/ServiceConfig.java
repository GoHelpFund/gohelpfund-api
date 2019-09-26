package com.gohelpfund.api.v1.donation_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

    @Value("${signing.key}")
    private String jwtSigningKey;

    @Value("${platform.env}")
    private String platformEnv;

    @Value("${topup.help.pk}")
    private String topUpHelpPk;

    @Value("${topup.help.addr}")
    private String topUpHelpAddr;

    @Value("${explorer.bitcoin}")
    private String explorerBitcoin;

    @Value("${explorer.help}")
    private String explorerHelp;

    public String getPlatformEnv() {
        return platformEnv;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public String getTopUpHelpPk() {
        return topUpHelpPk;
    }

    public String getTopUpHelpAddr() {
        return topUpHelpAddr;
    }

    public String getExplorerBitcoin() {
        return explorerBitcoin;
    }

    public String getExplorerHelp() {
        return explorerHelp;
    }
}
