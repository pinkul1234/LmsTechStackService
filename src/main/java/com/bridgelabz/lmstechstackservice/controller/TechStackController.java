package com.bridgelabz.lmstechstackservice.controller;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import com.bridgelabz.lmstechstackservice.service.ITechStackService;
import com.bridgelabz.lmstechstackservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techstack")
public class TechStackController {
    @Autowired
    ITechStackService techStackService;
    @PostMapping("/addtechstack")
    public ResponseEntity<Response> addTechStack(@RequestHeader String token, @RequestBody TechStackDto techStackDto){
        Response response = techStackService.addTechStack(token, techStackDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateTechStack/{id}")
    public ResponseEntity<Response> updateTechStack(@RequestHeader long id, @PathVariable String token, @RequestBody TechStackDto techStackDto) {
        Response response = techStackService.updateTechStack(id, token, techStackDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/gettechstackdata")
    public ResponseEntity<List<?>> getAllTechStack(@RequestHeader String token){
        List<TechStackModel> response = techStackService.getAllTechStack(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/deletetechstack")
    public ResponseEntity<Response> deleteTechStack(@PathVariable long id, @RequestHeader String token){
        Response response = techStackService.deleteTechStack(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}