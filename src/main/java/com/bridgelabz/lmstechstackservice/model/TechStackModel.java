package com.bridgelabz.lmstechstackservice.model;

import com.bridgelabz.lmstechstackservice.dto.TechStackDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "techstack")
@Data
public class TechStackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime creatorStamp;
    private String imagePath;
    private boolean isStatus;
    private String techName;


    public TechStackModel(TechStackDto techStackDto){
        this.id = techStackDto.getId();
        this.techName = techStackDto.getTechName();
        this.creatorStamp = techStackDto.getCreatorStamp();
        this.isStatus = techStackDto.isStatus();
        this.imagePath = techStackDto.getImagePath();
    }

    public TechStackModel() {

    }
}