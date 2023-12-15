package com.assetmanager.auth;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.assetmanager.app.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTUtil {

    //private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    //TODO - Testing only. Must be replaced in prod using System.getenv("SECRET_KEY");
    private static final String SECRET_KEY = "buLLNmd1SSwmE7FiZ3j2IXKyyr9eEzQJrunawn4E8bzsSH9DTlxhvLcJ4jt5HwD";

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User userDetails
    ) {
        extraClaims.put("role",userDetails.getUserRole());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256,getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String jwtToken, User userDetails) {
        try {
            final String username = extractUsername(jwtToken);
            final String role = extractRoleClaim(jwtToken);


            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(jwtToken)
                    && role.equals(userDetails.getUserRole().name());
        } catch (JwtException e) {
            return false;
        }

    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    public String extractRoleClaim(String jwtToken) {
        return extractClaim(jwtToken, claims -> (String) claims.get("role"));
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
