package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.dto.Attribute;
import com.example.onlinefitnessback.repositories.AttributeRepository;
import com.example.onlinefitnessback.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {
    private final ModelMapper modelMapper;
    private final AttributeRepository attributeRepository;

    public AttributeServiceImpl(ModelMapper modelMapper, AttributeRepository attributeRepository) {
        this.modelMapper = modelMapper;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<Attribute> getAllByCategoryId(Integer id) {
        return attributeRepository.getAllByCategory_Id(id).stream().map(a->modelMapper.map(a, Attribute.class)).collect(Collectors.toList());
    }
}
