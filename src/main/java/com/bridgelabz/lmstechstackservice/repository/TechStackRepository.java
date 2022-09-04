package com.bridgelabz.lmstechstackservice.repository;

import com.bridgelabz.lmstechstackservice.model.TechStackModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackRepository extends JpaRepository<TechStackModel, Long> {
}