package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.responses.ScholarshipResponse;
import com.NextStepEdu.models.ScholarshipModel;
import com.NextStepEdu.services.ScholarshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/scholarship")
@AllArgsConstructor
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping
    public ResponseEntity<List<ScholarshipResponse>> getAllScholarships() {
        List<ScholarshipResponse> scholarships = scholarshipService.findAll()
                .stream()
                .map(ScholarshipResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(scholarships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipResponse> getScholarshipById(@PathVariable Integer id) {
        ScholarshipModel scholarship = scholarshipService.findById(id);
        return ResponseEntity.ok(ScholarshipResponse.fromEntity(scholarship));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ScholarshipResponse> getScholarshipBySlug(@PathVariable String slug) {
        ScholarshipModel scholarship = scholarshipService.findBySlug(slug);
        return ResponseEntity.ok(ScholarshipResponse.fromEntity(scholarship));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ScholarshipResponse> createScholarship(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String benefits,
            @RequestParam(required = false) String requirements,
            @RequestParam(required = false) String howToApply,
            @RequestParam(required = false) String applyLink,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline,
            @RequestParam Integer programId,
            @RequestParam Integer universityId,
            @RequestParam(required = false) MultipartFile logo,
            @RequestParam(required = false) MultipartFile coverImage
    ) {
        ScholarshipModel scholarship = scholarshipService.create(
                name, description, level, benefits, requirements,
                howToApply, applyLink, status, deadline,
                programId, universityId, logo, coverImage
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ScholarshipResponse.fromEntity(scholarship));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ScholarshipResponse> updateScholarship(
            @PathVariable Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String benefits,
            @RequestParam(required = false) String requirements,
            @RequestParam(required = false) String howToApply,
            @RequestParam(required = false) String applyLink,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline,
            @RequestParam(required = false) Integer programId,
            @RequestParam(required = false) Integer universityId,
            @RequestParam(required = false) MultipartFile logo,
            @RequestParam(required = false) MultipartFile coverImage
    ) {
        ScholarshipModel scholarship = scholarshipService.update(
                id, name, description, level, benefits, requirements,
                howToApply, applyLink, status, deadline,
                programId, universityId, logo, coverImage
        );
        return ResponseEntity.ok(ScholarshipResponse.fromEntity(scholarship));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholarship(@PathVariable Integer id) {
        scholarshipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
