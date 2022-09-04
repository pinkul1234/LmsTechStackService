package com.bridgelabz.lmstechstackservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechStackDto {
    private int id;
    private LocalDateTime creatorStamp;
    private String imagePath;
    private boolean status;
    private String techName;
}