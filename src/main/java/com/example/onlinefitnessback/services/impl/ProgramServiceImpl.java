package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.dto.Picture;
import com.example.onlinefitnessback.models.dto.Program;
import com.example.onlinefitnessback.models.entities.CategoryEntity;
import com.example.onlinefitnessback.models.entities.PictureEntity;
import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.ProgramRequest;
import com.example.onlinefitnessback.repositories.CategoryRepository;
import com.example.onlinefitnessback.repositories.PictureRepository;
import com.example.onlinefitnessback.repositories.ProgramRepository;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.ProgramService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {
    private final ModelMapper modelMapper;
    private final ProgramRepository programRepository;
    private final PictureRepository pictureRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public ProgramServiceImpl(ModelMapper modelMapper, ProgramRepository programRepository, PictureRepository pictureRepository) {
        this.modelMapper = modelMapper;
        this.programRepository = programRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll().stream().map(a ->modelMapper.map(a,Program.class)).collect(Collectors.toList());
    }

    @Override
    public ProgramEntity addNewProgram(ProgramRequest programRequest) {
        System.out.println("uslooo");
        // Učitaj kategoriju iz baze prema ID-u
        CategoryEntity category = categoryRepository.findById(programRequest.getId_category())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Učitaj korisnika (ako je potrebno)
        UserEntity user = userRepository.findById(programRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kreiraj program
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setIdProgram(null);
        programEntity.setProgramName(programRequest.getProgramName());
        programEntity.setDescription(programRequest.getDescription());
        programEntity.setProgramPrice(programRequest.getProgramPrice());
        programEntity.setLevel(programRequest.getLevel());
        programEntity.setDuration(programRequest.getDuration());
        programEntity.setLocation(programRequest.getLocation());
        programEntity.setInstructorInformation(programRequest.getInstructorInformation());
        programEntity.setPhone(programRequest.getPhone());
        programEntity.setVideoUrl(programRequest.getVideo_url());
        programEntity.setCategory(category); // Postavi kategoriju
        programEntity.setUser(user); // Postavi korisnika

        // Spasi program u bazu
        programRepository.saveAndFlush(programEntity);
        entityManager.refresh(programEntity);
        System.out.println(programEntity.getIdProgram());

        // Dodaj slike ako postoje
        if (programRequest.getPictures() != null && !programRequest.getPictures().isEmpty()) {
            // Prolazi kroz Base64 stringove slika
            for (Picture imageBase64 : programRequest.getPictures()) {
                // Kreiraj PictureEntity objekat
                PictureEntity pictureEntity = new PictureEntity();
                pictureEntity.setUrl(imageBase64.getUrl()); // Setuj Base64 string slike
                pictureEntity.setProgram(programEntity); // Postavi vezu sa programom

                // Spasi slike u bazu
                pictureRepository.saveAndFlush(pictureEntity);
            }
        }

        return programEntity;
    }


    @Override
    public List<Program> getMyPrograms(Integer userId) {
        List<ProgramEntity> myPrograms = programRepository.findAllByUserId(userId);
        return myPrograms.stream()
                .map(entity -> modelMapper.map(entity, Program.class))
                .collect(Collectors.toList());
    }



}
