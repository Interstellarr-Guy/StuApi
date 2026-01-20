package fantasy.StuApi.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fantasy.StuApi.dto.LoginRequest;
import fantasy.StuApi.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;

  public AuthController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginRequest req) {

    // TEMP: hardcoded user (later DB)
    if ("admin".equals(req.username()) && "admin123".equals(req.password())) {
      String token = jwtUtil.generateToken(req.username());
      return Map.of("token", token);
    }

    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
  }
}
