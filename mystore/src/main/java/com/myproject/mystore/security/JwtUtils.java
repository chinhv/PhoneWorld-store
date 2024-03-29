package com.myproject.mystore.security;


import java.time.Instant;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.myproject.mystore.service.CustomUserDetails;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final String jwtSecret = "Spring";
    private final String jwtCookie = "SpringBoot";
    private final String jwtExpiration = "84600000";
    public String getJwtFromCookie(HttpServletRequest req){
        Cookie cookie = WebUtils.getCookie(req, jwtCookie);
        if(cookie != null){
            return cookie.getValue();
        }else{
            return null;
        }
    }
    public ResponseCookie generateJwtCookie(CustomUserDetails userPrincipal){
        String jwt = generateTokenFromEmail(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(24 * 60 * 60).httpOnly(true).sameSite("None").secure(true).build();
        return cookie;
    }
    public ResponseCookie cleanJwtCookie(){
        ResponseCookie cookie = ResponseCookie.from(jwtCookie,null).path("/").build();
        return cookie;
    }
    public String getEmailFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken) {
        try {
          Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
          return true;
        } catch (SignatureException e) {
          logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
          logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
          logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
          logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
          logger.error("JWT claims string is empty: {}", e.getMessage());
        }
    
        return false;
    }
    public String generateTokenFromEmail(String email){
        Instant instant = Instant.now().plusMillis(Long.parseLong(jwtExpiration));
        Date expirationDate = Date.from(instant);
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
}
