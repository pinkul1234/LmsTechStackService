package com.bridgelabz.lmstechstackservice.controller;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import com.bridgelabz.lmstechstackservice.service.ITechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techstack")
public class TechStackController {
    @Autowired
    ITechStackService techStackService;
    @PostMapping("/addtechstack")
    public TechStackModel addTechStack(@RequestHeader String token, @RequestBody TechStackDto techStackDto){
        return techStackService.addTechStack(token, techStackDto);
    }
    @PutMapping("/updatetechstack{id}")
    public TechStackModel updateTechStack(@RequestHeader String token, @RequestBody TechStackDto techStackDto, @PathVariable long id){
        return techStackService.updateTechStack(id, token, techStackDto);
    }
    @GetMapping("/gettechstackdata")
    public List<TechStackModel> getAllTechStack(@RequestHeader String token){
        return techStackService.getAllTechStack(token);
    }
    @DeleteMapping("/deletetechstack")
    public TechStackModel deleteTechStack(@PathVariable long id, @RequestHeader String token){
        return techStackService.deleteTechStack(id, token);
    }


}