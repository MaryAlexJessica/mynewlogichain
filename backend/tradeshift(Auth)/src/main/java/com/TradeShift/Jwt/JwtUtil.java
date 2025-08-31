package com.TradeShift.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long jwtExpirationMs;
	
	private Key getSignInKey() {
	 byte[] keyBytes = Base64.getDecoder().decode(secret);
	 return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(UserDetails userDetails) {
	 Map<String, Object> claims = new HashMap<>();
	 claims.put("roles", userDetails.getAuthorities());
	 return Jwts.builder()
	     .setClaims(claims)
	     .setSubject(userDetails.getUsername())
	     .setIssuedAt(new Date())
	     .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	     .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	     .compact();
	}
	
	public String extractUsername(String token) {
	 return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	 Claims claims = Jwts.parserBuilder()
	     .setSigningKey(getSignInKey())
	     .build()
	     .parseClaimsJws(token)
	     .getBody();
	 return resolver.apply(claims);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
	 String username = extractUsername(token);
	 return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
	 Date exp = extractClaim(token, Claims::getExpiration);
	 return exp.before(new Date());
	}
}
