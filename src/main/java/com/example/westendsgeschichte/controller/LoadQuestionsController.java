package com.example.westendsgeschichte.controller;

import com.example.westendsgeschichte.model.*;
import com.example.westendsgeschichte.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class LoadQuestionsController {

    private final LoadQuestionsService service;

    public LoadQuestionsController(LoadQuestionsService service) {
        this.service = service;
    }

    @PostMapping
    public Questions create(@RequestBody Questions question) {
        return service.save(question);
    }

    @GetMapping
    public List<Questions> getAll() {
        return service.getAll();
    }
}
