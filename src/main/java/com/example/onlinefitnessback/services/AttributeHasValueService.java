package com.example.onlinefitnessback.services;


import com.example.onlinefitnessback.models.dto.AttributeHasValue;

import java.util.List;

public interface AttributeHasValueService {
    List<AttributeHasValue> getAllByAttributeId(Integer id);
}
