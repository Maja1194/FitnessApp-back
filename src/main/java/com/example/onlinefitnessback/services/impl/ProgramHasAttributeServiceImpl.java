package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.dto.Attribute;
import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.models.dto.ProgramHasAttribute;
import com.example.onlinefitnessback.models.entities.ProgramHasAttributeEntity;
import com.example.onlinefitnessback.models.requests.ProgramHasAttributeRequest;
import com.example.onlinefitnessback.repositories.ProgramHasAttributeRepository;
import com.example.onlinefitnessback.services.ProgramHasAttributeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramHasAttributeServiceImpl implements ProgramHasAttributeService {
    private final ModelMapper modelMapper;
    private final ProgramHasAttributeRepository programHasAttributeRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LogManager.getLogger(ProgramHasAttributeServiceImpl.class);

    public ProgramHasAttributeServiceImpl(ModelMapper modelMapper, ProgramHasAttributeRepository programHasAttributeRepository) {
        this.modelMapper = modelMapper;
        this.programHasAttributeRepository = programHasAttributeRepository;
    }

    @Override
    public void addAttribute(ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication) {
        ProgramHasAttributeEntity programHasAttributeEntity = modelMapper.map(programHasAttributeRequest, ProgramHasAttributeEntity.class);

        programHasAttributeEntity.setId(null);

        programHasAttributeRepository.saveAndFlush(programHasAttributeEntity);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
    }

    @Override
    public List<ProgramHasAttribute> getAttributesByProgramId(Integer programId) {
        List<ProgramHasAttributeEntity> entities = programHasAttributeRepository.findByProgram_IdProgram(programId);

        return entities.stream().map(entity -> {
            ProgramHasAttribute dto = new ProgramHasAttribute();
            dto.setId(entity.getId());
            dto.setValue(entity.getValue());

            Attribute attributeDTO = new Attribute();
            attributeDTO.setId(entity.getAttribute().getId());
            attributeDTO.setAttributeName(entity.getAttribute().getAttributeName());
            dto.setAttribute(attributeDTO);

            return dto;
        }).collect(Collectors.toList());
    }

}
