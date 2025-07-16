package com.smarttaskpro.smarttask.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//purpose=
//generate Jwt token for user(after logging in)
//extract username(email) from token
//validate token to check if its real amd not expired or tampered with
@Component //tells Spring:"Please create and manage an object of this class .so I can inject it elsewhere."
public class JwtUtil {
    private final String jwtSecret = "YourSuperSecretKeyForJwtSigning_ChangeThisToSomethingStrong!";//  this is secret key. it is used to digitally sign the JWT so nobody can forge it.In prod its kept secret in .env / config file.
    private final int jwtExpirationMs = 86400000; // 24 hours token expires after it
    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); //converts secret string to key object,hmacShaKeyFo makes sure key is secure enough for HS256 algorithm.



    //Generating token
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    //extract username from token

    public String getUsernameFromJwt(String token){
        return Jwts.parserBuilder().setSigningKey(key).build() //parses token, validates signature with key,
                .parseClaimsJws(token).getBody().getSubject(); //gets payload body and returns subject which is username
    }


    public boolean validateJwtToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; //tries to parse token.if expired modified or invalid signature, throws ecxceopttion
        }catch (JwtException | IllegalArgumentException e){

            return false;
        }

    }





}
