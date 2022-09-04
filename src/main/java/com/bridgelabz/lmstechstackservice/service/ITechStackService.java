package com.bridgelabz.lmstechstackservice.service;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;

import java.util.List;

public interface ITechStackService {
    TechStackModel addTechStack(String token, TechStackDto techStackDto);

    TechStackModel updateTechStack(long id, String token, TechStackDto techStackDto);

    List<TechStackModel> getAllTechStack(String token);

    TechStackModel deleteTechStack(long id, String token);
}