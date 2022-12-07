package com.example.kardex.configuration;



import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	private final static String ACCESS_TOKEN_SECRET = "caa5cc97b8bb592b0410503ece70458126f834d5";
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	public static String createToken(String nombre, String usuario, int id) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("id", id);
		extra.put("nombre", nombre);
		
		return Jwts.builder()
				.setSubject(usuario)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
	} 
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims  = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String usuario = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
		} catch (Exception e) {
			return null;
		}
		
		
		
	}
}
