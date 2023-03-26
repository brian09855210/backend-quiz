package com.backend.quiz.framework.util;

import io.jsonwebtoken.*;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.message.AuthException;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public class JwtUtil {
    private static final long EXPIRATIONTIME = 30 * 60 * 1000;  // 30分鐘
    private static final String SECRET = "BackendQuiz";
    private static final SignatureAlgorithm signature = SignatureAlgorithm.HS256;
    private static final byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
    private static final Key secretKey = new SecretKeySpec(secretBytes, signature.getJcaName());

    // JWT產生方法
    public static String createToken(String account) {
        return Jwts.builder()
                .setSubject(account)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(signature, secretKey)
                .compact();
    }

    // JWT驗證方法
    public static Map<String, Object> parseToken(String token) throws AuthException {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretBytes)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
        return claims;
    }
}
