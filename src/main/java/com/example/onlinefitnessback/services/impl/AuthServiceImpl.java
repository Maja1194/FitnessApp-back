package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.exceptions.NotFoundException;
import com.example.onlinefitnessback.exceptions.UnauthorizedException;
import com.example.onlinefitnessback.models.dto.LoginResponse;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.LoginRequest;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.AuthService;
import com.example.onlinefitnessback.util.LoggingUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class AuthServiceImpl implements AuthService {
    @Value("${authorization.token.expiration-time}")
    private String tokenExpirationTime;
    @Value("${authorization.token.secret}")
    private String tokenSecret;
    private Key key;
    @Autowired
    private JavaMailSender mailSender;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        if (tokenSecret == null || tokenSecret.isEmpty()) {
            throw new IllegalArgumentException("Token secret is not provided");
        }
        this.key = Keys.hmacShaKeyFor(tokenSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void sendActivationEmail(String email, String username) {
        String token = generateActivationToken(username);
        String activationLink = "http://localhost:4200/activate?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Activate your account");
        message.setText(buildEmailBody(activationLink));

        mailSender.send(message);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(NotFoundException::new);
            if (userEntity.getStatus().equals("ACTIVE")) {
                LoginResponse response = modelMapper.map(userEntity, LoginResponse.class);
                response.setToken(generateJwt(userEntity));
                logger.info("LOGIN USER " + userEntity.getId());
                return response;
            } else {
                sendActivationEmail(userEntity.getUsername(), userEntity.getEmail());
                logger.info("LOGIN USER SEND ACTIVATION LINK " + userEntity.getId());
                return null;
            }
        } catch (Exception e) {
            LoggingUtil.logException(e, getClass());
            throw new UnauthorizedException();
        }
    }

    @Override
    public User activateAccount(String token) {
        try {
            Claims claims = parseJWT(token);
            String username = claims.getSubject();
            UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
            userEntity.setStatus("ACTIVE");
            userEntity = userRepository.saveAndFlush(userEntity);
            logger.info("User activated: " + userEntity.getId());
            return modelMapper.map(userEntity, User.class);
        } catch (Exception e) {
            logger.error("Error during activation", e);
            return null;
        }
    }

    private String buildEmailBody(String activationLink) {
        return "Dear user,\n\n" +
                "Thank you for registering for our fitness app. To activate your account, please click on the link below:\n\n" +
                activationLink + "\n\n" +
                "If you did not request this, please ignore this email.\n\n" +
                "Best regards,\n" +
                "The Fitness App Team";
    }

    private String generateActivationToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)*2))
                .signWith(key)
                .compact();
    }

    private String generateJwt(UserEntity user) {
        return Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .claim("role", user.getStatus())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)))
                .signWith(key)
                .compact();
    }

    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
