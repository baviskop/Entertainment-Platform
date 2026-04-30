package com.long1dep.youtuberef11.common.utils;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.exception.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
//TODO:1
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtil {

    private static SecretKey getSigningKey(String jwtSecret) {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public static String generateJwtToken(AccountEntity account, String jwtSecret, int jwtExpiration) {
        return Jwts.builder()
                .subject(account.getUuid())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(getSigningKey(jwtSecret))
                .compact();
    }
    public static String getUserUuidFromJwtToken(String token, String jwtSecret) {
        return Jwts.parser().verifyWith(getSigningKey(jwtSecret)).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public static void validateJwtToken(String token, String jwtSecret) {
        try {
            Jwts.parser().verifyWith(getSigningKey(jwtSecret)).build().parseSignedClaims(token);
        }catch (Exception e){
            throw new AuthenticationException("Invalid JWT token");
        }
    }
}
