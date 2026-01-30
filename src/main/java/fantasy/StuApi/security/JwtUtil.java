package fantasy.StuApi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("roles","ROLE_" + role) //2301
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
        .compact();
  }

  @PostConstruct
  public void checkSecret() {
    if (secret == null || secret.length() < 32) {
      throw new IllegalStateException("JWT secret is missing or too short");
    }
  }
    //Latest adding 2101
  public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
	        .build()
	        .parseClaimsJws(token)
	        .getBody()
	        .getSubject();
	}
  
  // new method 30-01 extract role
  public String extractRole(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .get("roles", String.class);
	}


  
}
