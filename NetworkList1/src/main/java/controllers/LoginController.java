package controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import networklist1.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @Value("${jwt.token}")
    private String key;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public LoginController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        String encodedPassword = passwordEncoder.encode("password to encode");
        boolean ifTheSame = passwordEncoder.matches(loginForm.getPassword(), "encodedPasswordFormDB");
        if(true){
            long millis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(millis))
                    .expiration(new Date(millis+3*60*1000))
                    .claim("id","userId")
                    .claim("role","USER")
                    .signWith(SignatureAlgorithm.HS256,key)
                    .compact();
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Wrong login or password!", HttpStatus.UNAUTHORIZED);
        }
    }
}