package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.Attribute;
import com.example.onlinefitnessback.models.dto.AttributeHasValue;
import com.example.onlinefitnessback.services.AttributeHasValueService;
import com.example.onlinefitnessback.services.AttributeService;
import com.example.onlinefitnessback.services.impl.AuthServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/attributes")
@RestController
public class AttributeController {
    private final AttributeHasValueService attributeHasValueService;
    private final AttributeService attributeService;
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    public AttributeController(AttributeHasValueService attributeHasValueService, AttributeService attributeService){
        this.attributeHasValueService = attributeHasValueService;
        this.attributeService = attributeService;
    }

    @GetMapping("/{id}/values")
    public ResponseEntity<List<AttributeHasValue>> getAllValuesByAttributeId(@PathVariable Integer id) {
        List<AttributeHasValue> attributes = attributeHasValueService.getAllByAttributeId(id);
        //logger.info(attributes);
//        System.out.println(attributes);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Attribute>> getAllAttributesByCategoryId(@PathVariable Integer id) {
        List<Attribute> attributes = attributeService.getAllByCategoryId(id);
        return ResponseEntity.ok(attributes);
    }
}
