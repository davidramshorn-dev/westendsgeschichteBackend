package com.example.westendsgeschichte.service;
import com.example.westendsgeschichte.model.*;
import com.example.westendsgeschichte.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoadQuestionsService {
private final LoadQuestionsRepository repo;

    public LoadQuestionsService(LoadQuestionsRepository repo) {
        this.repo = repo;
    }

    public Questions save(Questions entry) {
        return repo.save(entry);
    }

    public List<Questions> getAll() {
        return repo.findAll();
    }
}
