package com.example.onlinefitnessback.services.impl;
import com.example.onlinefitnessback.models.requests.UserHasProgramRequest;
import jakarta.transaction.Transactional;
import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.models.dto.Program;
import com.example.onlinefitnessback.models.entities.UserHasProgramEntity;
import com.example.onlinefitnessback.repositories.UserHasProgramRepository;
import com.example.onlinefitnessback.services.UserHasProgramService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserHasProgramServiceImpl implements UserHasProgramService {
    private final ModelMapper modelMapper;
    private final UserHasProgramRepository userHasProgramRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserHasProgramServiceImpl(ModelMapper modelMapper, UserHasProgramRepository userHasProgramRepository) {
        this.modelMapper = modelMapper;
        this.userHasProgramRepository = userHasProgramRepository;
    }

    @Override
    public boolean userHasProgram(Integer userId, Integer programId, Authentication authentication) {
        return userHasProgramRepository.existsByUserIdAndProgramIdProgram(userId, programId);
    }

    @Override
    public List<Program> getPurchasedPrograms(Integer id, Authentication auth) {
        List<UserHasProgramEntity> userPrograms = userHasProgramRepository.getAllByUserId(id);
        return userPrograms.stream()
                .map(userHasProgram -> (modelMapper.map(userHasProgram.getProgram(), Program.class)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void addUserHasProgram(UserHasProgramRequest userHasProgramRequest, Authentication auth) {
        if (userHasProgramRequest.getProgramId() == null || userHasProgramRequest.getUserId() == null) {
            throw new IllegalArgumentException("Program ID and User ID must not be null.");
        }

        UserHasProgramEntity userHasProgramEntity = modelMapper.map(userHasProgramRequest, UserHasProgramEntity.class);
        userHasProgramEntity.setId(null);
        userHasProgramEntity.setStartDate(Date.valueOf(LocalDate.now()));
        userHasProgramRepository.saveAndFlush(userHasProgramEntity);
        entityManager.refresh(userHasProgramEntity);
    }

}
