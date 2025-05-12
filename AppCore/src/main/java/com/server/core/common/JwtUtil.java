package com.server.core.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

	public static String generateToken(String email, String name) {
		return Jwts.builder()
				.setSubject(email)
				.claim("name", name)
				.claim("email", email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}

	public static Jws<Claims> validateToken(String token) throws JwtException {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
	}
}
