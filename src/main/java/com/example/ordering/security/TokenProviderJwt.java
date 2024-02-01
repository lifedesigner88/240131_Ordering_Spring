package com.example.ordering.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TokenProviderJwt {

    public String createdToken(String email, String role){
//        claims: 클레임은 토큰 사용자에 대한 속성이나 데이터 포함, 주로 페이로드를 의미.

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 30 * 60 * 1000L))
                .signWith(SignatureAlgorithm.HS256, "sejong")
                .compact();
    }

}
