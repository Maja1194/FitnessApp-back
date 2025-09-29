package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.exceptions.UnauthorizedException;
import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.JwtUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;



@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public JwtUserDetailsServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(userRepository.findByUsername(username).orElseThrow(UnauthorizedException::new), JwtUser.class);
    }
}