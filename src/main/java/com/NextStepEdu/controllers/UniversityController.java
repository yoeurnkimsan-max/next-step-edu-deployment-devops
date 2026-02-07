package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.UniversityRequest;
import com.NextStepEdu.dto.responses.UniversityResponse;
import com.NextStepEdu.services.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UniversityResponse> createUniversity(
            @Valid @ModelAttribute UniversityRequest request,
            @RequestParam(value = "logoUrl", required = false) MultipartFile logoUrl,
            @RequestParam(value = "coverImageUrl", required = false) MultipartFile coverImageUrl) {
        UniversityResponse response = universityService.createUniversity(request, logoUrl, coverImageUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> getUniversityById(@PathVariable Integer id) {
        UniversityResponse response = universityService.getUniversityById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<UniversityResponse> getUniversityBySlug(@PathVariable String slug) {
        UniversityResponse response = universityService.getUniversityBySlug(slug);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UniversityResponse>> getAllUniversities() {
        List<UniversityResponse> responses = universityService.getAllUniversities();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UniversityResponse>> searchUniversities(@RequestParam String keyword) {
        List<UniversityResponse> responses = universityService.searchUniversities(keyword);
        return ResponseEntity.ok(responses);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UniversityResponse> updateUniversity(
            @PathVariable Integer id,
            @Valid @ModelAttribute UniversityRequest request,
            @RequestParam(value = "logoUrl", required = false) MultipartFile logoUrl,
            @RequestParam(value = "coverImageUrl", required = false) MultipartFile coverImageUrl) {
        UniversityResponse response = universityService.updateUniversity(id, request, logoUrl, coverImageUrl);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
}
