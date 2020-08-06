package com.example.springsecurityjwt.config;

import lombok.Data;

@Data
public class TokenProperties {
    private String secretKey;
    private int tokenExpireSecond;
    private String tokenHeaderPrefix;
    private String authorizationHeaderName;
    private int refreshTokenExpiredSecond;
    private String refreshHeaderName;
    private String userId;
}
