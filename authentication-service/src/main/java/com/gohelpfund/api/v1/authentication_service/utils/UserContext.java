package com.gohelpfund.api.v1.authentication_service.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID      = "ghf-correlation-id";
    public static final String AUTH_TOKEN          = "Authorization";
    public static final String USER_ID             = "ghf-user-id";
    public static final String USER_PASS_CHANGED   = "ghf-user-pass-changed";
    public static final String USER_TYPE           = "ghf-user-type";

    private static final ThreadLocal<String> correlationId= new ThreadLocal<>();
    private static final ThreadLocal<String> authToken= new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> userPassChanged = new ThreadLocal<>();
    private static final ThreadLocal<String> userType = new ThreadLocal<>();

    public static String getCorrelationId() { return correlationId.get(); }
    public static void setCorrelationId(String cid) {correlationId.set(cid);}

    public static String getAuthToken() { return authToken.get(); }
    public static void setAuthToken(String aToken) {authToken.set(aToken);}

    public static String getUserId() { return userId.get(); }
    public static void setUserId(String aUser) {userId.set(aUser);}

    public static String isUserPassChanged() { return userPassChanged.get(); }
    public static void setUserPassChanged(String passChanged) {userPassChanged.set(passChanged);}

    public static String getUserType() { return userType.get(); }
    public static void setUserType(String aType) {userType.set(aType);}


}