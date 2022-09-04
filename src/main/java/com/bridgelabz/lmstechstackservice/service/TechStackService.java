package com.bridgelabz.lmstechstackservice.service;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.exception.TechStackNotFoundException;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import com.bridgelabz.lmstechstackservice.repository.TechStackRepository;
import com.bridgelabz.lmstechstackservice.service.ITechStackService;
import com.bridgelabz.lmstechstackservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechStackService implements ITechStackService {
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    TechStackRepository techStackRepository;
    @Override
    public TechStackModel addTechStack(String token, TechStackDto techStackDto) {
        long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechStackPresent = techStackRepository.findById(userId);
        if (isTechStackPresent.isPresent()) {
            TechStackModel techStackModel = new TechStackModel(techStackDto);
            techStackRepository.save(techStackModel);
            return techStackModel;
        }
        throw new TechStackNotFoundException(400, "Techstack Added Unsuccessfull");
    }

    @Override
    public TechStackModel updateTechStack(long id, String token, TechStackDto techStackDto) {
        long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isAdminPresent =techStackRepository.findById(userId);
        if (isAdminPresent.isPresent()) {
            Optional<TechStackModel> isTechStackPresent = techStackRepository.findById(id);
            if (isTechStackPresent.isPresent()) {
                isTechStackPresent.get().setId(techStackDto.getId());
                isTechStackPresent.get().setCreatorStamp(techStackDto.getCreatorStamp());
                isTechStackPresent.get().setImagePath(techStackDto.getImagePath());
                isTechStackPresent.get().setStatus(techStackDto.isStatus());
                isTechStackPresent.get().setTechName(techStackDto.getTechName());
                techStackRepository.save(isTechStackPresent.get());
                return isTechStackPresent.get();
            }
            throw new TechStackNotFoundException(400, "TechStack Not Found");
        }
        throw new TechStackNotFoundException(400, "Failed");
    }

    @Override
    public List<TechStackModel> getAllTechStack(String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechStackPresent = techStackRepository.findById(userId);
        if (isTechStackPresent.isPresent()) {
            List<TechStackModel> getAllTechStack = techStackRepository.findAll();
            return getAllTechStack;
        } else {
            throw new TechStackNotFoundException(400, "Techstack Not Available");
        }
    }

    @Override
    public TechStackModel deleteTechStack(long id, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isAdmin = techStackRepository.findById(userId);
        if (isAdmin.isPresent()) {
            Optional<TechStackModel> isTechStackPresent = techStackRepository.findById(id);
            if (isTechStackPresent.isPresent()) {
                techStackRepository.delete(isTechStackPresent.get());
                return isTechStackPresent.get();
            }
            throw new TechStackNotFoundException(400, "TechStack does not found");
        }
        throw new TechStackNotFoundException(400, "Failed");
    }
}
