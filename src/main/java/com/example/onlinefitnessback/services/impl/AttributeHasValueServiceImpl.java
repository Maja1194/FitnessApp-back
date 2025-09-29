package com.example.onlinefitnessback.services.impl;


import com.example.onlinefitnessback.models.dto.AttributeHasValue;
import com.example.onlinefitnessback.repositories.AttributeHasValueRepository;
import com.example.onlinefitnessback.services.AttributeHasValueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeHasValueServiceImpl implements AttributeHasValueService {
    private final ModelMapper modelMapper;
    private final AttributeHasValueRepository attributeHasValueRepository;

    public AttributeHasValueServiceImpl(ModelMapper modelMapper, AttributeHasValueRepository attributeHasValueRepository) {
        this.modelMapper=modelMapper;
        this.attributeHasValueRepository = attributeHasValueRepository;
    }
    @Override
    public List<AttributeHasValue> getAllByAttributeId(Integer id) {
        return attributeHasValueRepository.getAllByAttribute_Id(id).stream().map(a->modelMapper.map(a, AttributeHasValue.class)).collect(Collectors.toList());

    }
}
