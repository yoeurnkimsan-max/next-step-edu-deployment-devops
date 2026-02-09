package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.FacultyRequest;
import com.NextStepEdu.dto.responses.FacultyResponse;
import com.NextStepEdu.services.FacultyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/faculties")
@AllArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FacultyResponse create(@Valid @RequestBody FacultyRequest request) {
        return facultyService.create(request);
    }

    @GetMapping("/{id}")
    public FacultyResponse getById(@PathVariable UUID id) {
        return facultyService.getById(id);
    }

    @GetMapping
    public List<FacultyResponse> getAll(@RequestParam(required = false) UUID universityId) {
        return facultyService.getAll(universityId);
    }

    @PutMapping("/{id}")
    public FacultyResponse update(@PathVariable UUID id, @Valid @RequestBody FacultyRequest request) {
        return facultyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        facultyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
