package com.bridgelabz.lmstechstackservice.service;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import com.bridgelabz.lmstechstackservice.util.Response;

import java.util.List;

public interface ITechStackService {
     Response addTechStack(String token, TechStackDto techStackDto);

    Response updateTechStack(long id, String token, TechStackDto techStackDto);

    List<TechStackModel> getAllTechStack(String token);

    Response deleteTechStack(long id, String token);
}