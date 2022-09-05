package com.bridgelabz.lmstechstackservice.service;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.exception.TechStackNotFoundException;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import com.bridgelabz.lmstechstackservice.repository.TechStackRepository;
import com.bridgelabz.lmstechstackservice.service.ITechStackService;
import com.bridgelabz.lmstechstackservice.util.Response;
import com.bridgelabz.lmstechstackservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TechStackService implements ITechStackService {
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    TechStackRepository techStackRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MailService mailService;
    @Override
    public Response addTechStack(String token, TechStackDto techStackDto) {
        boolean isTechStackPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isTechStackPresent) {
            TechStackModel techStackModel = new TechStackModel(techStackDto);
            techStackRepository.save(techStackModel);
            return new Response("Success", 200, techStackModel);
        }
        throw new TechStackNotFoundException(400, "Techstack Added Unsuccessfull");
    }

    @Override
    public Response updateTechStack(long id, String token, TechStackDto techStackDto) {
        boolean isTechStackPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isTechStackPresent) {
            Optional<TechStackModel> isTechStack = techStackRepository.findById(id);
            if (isTechStack.isPresent()) {
                isTechStack.get().setId(techStackDto.getId());
                isTechStack.get().setCreatorStamp(techStackDto.getCreatorStamp());
                isTechStack.get().setImagePath(techStackDto.getImagePath());
                isTechStack.get().setStatus(techStackDto.isStatus());
                isTechStack.get().setTechName(techStackDto.getTechName());
                techStackRepository.save(isTechStack.get());
                return new Response("success", 200, isTechStack);
            }
            throw new TechStackNotFoundException(400, "TechStack Not Found");
        }
        throw new TechStackNotFoundException(400, "Failed");
    }

    @Override
    public List<TechStackModel> getAllTechStack(String token) {
        boolean isTechStackPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isTechStackPresent) {
            List<TechStackModel> isTechStack = techStackRepository.findAll();
            return isTechStack;
        } else {
            throw new TechStackNotFoundException(400, "Techstack Not Available");
        }
    }

    @Override
    public Response deleteTechStack(long id, String token) {
        boolean isTechStackPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isTechStackPresent) {
            Optional<TechStackModel> isTechStack = techStackRepository.findById(id);
            if (isTechStack.isPresent()) {
                techStackRepository.delete(isTechStack.get());
                return new Response("success", 200, isTechStack.get());
            }
            throw new TechStackNotFoundException(400, "TechStack does not found");
        }
        throw new TechStackNotFoundException(400, "Failed");
    }
}
