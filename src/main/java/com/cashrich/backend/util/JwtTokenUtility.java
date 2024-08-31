package com.cashrich.backend.util;

import com.cashrich.backend.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.foreign.MemorySegment;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtility {


    public static String jwtSecretKey = "cc871389e294a578dd92d96d38ea7c22ddd9e3be6fce1dc14254e79ab1001bd1";


    private static final long EXPIRATION_TIME = 172800000; // 2 day expiry

    public static Map<String,String> createJwtForTheUser(User user){

        Map<String,Object> claims = new HashMap<>();

        claims.put("user_name",user.getUserName());
        claims.put("email",user.getEmail());
        claims.put("First Name",user.getFirstName());
        claims.put("Last Name",user.getLastName());

        String jwt = createToken(claims,user.getEmail());

        Map<String,String> jwtMap = new HashMap<>();

        jwtMap.put("token",jwt);

        return jwtMap;
    }


    private static String createToken(Map<String, Object> claims, String subject) {


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    public static String getUserForCurrentSession(){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
