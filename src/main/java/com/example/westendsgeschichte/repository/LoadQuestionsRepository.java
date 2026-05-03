package com.example.westendsgeschichte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.westendsgeschichte.model.Questions;

public interface LoadQuestionsRepository extends JpaRepository<Questions, Long> {
}
