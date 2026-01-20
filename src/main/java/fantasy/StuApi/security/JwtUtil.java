package fantasy.StuApi.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

  private final String SECRET = "CHANGE_THIS_SECRET_KEY_1234567891";

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
        .compact();
  }
  
  @PostConstruct
  public void checkSecret() {
    if (SECRET == null || SECRET.length() < 32) {
      throw new IllegalStateException("JWT secret is missing or too short");
    }
  }

}
