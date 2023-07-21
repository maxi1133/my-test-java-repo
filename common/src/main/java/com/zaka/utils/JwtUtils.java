package com.zaka.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.zaka.config.JwtConfig;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {

    @Autowired
    Algorithm algorithm;

    @Autowired
    JwtConfig jwtConfig;

    /**
     *
     * @param userInfo
     * @return
     */
    public String generateTokenFromUserInfo(Object userInfo) {
        final Date now = new Date();
        final Date plusDay = Date.from(now.toInstant().plus(jwtConfig.getExpiredIn(), ChronoUnit.DAYS));

        return JWT
                .create()
                .withPayload(CommonUtils.convertObjectToHashMap(userInfo))
                .withIssuedAt(now)
                .withExpiresAt(plusDay)
                .sign(algorithm);
    }

    public void expiredNow() {

    }
}
