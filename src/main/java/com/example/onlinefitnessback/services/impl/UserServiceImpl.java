package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.exceptions.NotFoundException;
import com.example.onlinefitnessback.exceptions.UnauthorizedException;
import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.PasswordUpdateRequest;
import com.example.onlinefitnessback.models.requests.SignUpRequest;
import com.example.onlinefitnessback.models.requests.UserUpdateRequest;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthServiceImpl authService;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthServiceImpl authService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public void signUp(SignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent() && userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already registered.");
        }

        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus("REQUESTED");

        userRepository.saveAndFlush(user);

        authService.sendActivationEmail(user.getEmail(), user.getUsername());
    }

    @Override
    public User updateUser(String username, UserUpdateRequest request, Authentication auth)  {
        UserEntity user = null;
        try {
            user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            user.setEmail(request.getEmail());
            user.setAvatar(request.getAvatar());
            user.setCity(request.getCity());
            JwtUser jwtUser = (JwtUser) auth.getPrincipal();
            logger.info("User " + jwtUser.getId() + " update.");
            return modelMapper.map(userRepository.saveAndFlush(user), User.class);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updatePassword(String username, PasswordUpdateRequest request, Authentication auth)  {
        UserEntity user = null;
        try {
            user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            logger.info("User " + jwtUser.getId() + " change password.");
            return modelMapper.map(userRepository.saveAndFlush(user), User.class);
        } else {
            logger.info("User " + jwtUser.getId() + " change password failed.");
            throw new UnauthorizedException("Current password is incorrect!");
        }
    }

    @Override
    public List<User> getAll(Authentication auth) {
        return userRepository.findAll().stream().map(a ->modelMapper.map(a, User.class)).collect(Collectors.toList());
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

}
