package com.gohelpfund.api.v1.authentication.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "ghf-correlation-id";
    public static final String AUTH_TOKEN     = "Authorization";
    public static final String USER_ID        = "ghf-user-id";
    public static final String ORG_ID         = "ghf-org-id";

    private static final ThreadLocal<String> correlationId= new ThreadLocal<>();
    private static final ThreadLocal<String> authToken= new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<>();


    public static String getCorrelationId() { return correlationId.get(); }
    public static void setCorrelationId(String cid) {correlationId.set(cid);}

    public static String getAuthToken() { return authToken.get(); }
    public static void setAuthToken(String aToken) {authToken.set(aToken);}

    public static String getUserId() { return userId.get(); }
    public static void setUserId(String aUser) {userId.set(aUser);}

    public static String getOrgId() { return orgId.get(); }
    public static void setOrgId(String aOrg) {orgId.set(aOrg);}


}