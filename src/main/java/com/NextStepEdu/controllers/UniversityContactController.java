package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.UniversityContactRequest;
import com.NextStepEdu.dto.responses.UniversityContactResponse;
import com.NextStepEdu.services.UniversityContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university-contacts")
@RequiredArgsConstructor
public class UniversityContactController {

    private final UniversityContactService contactService;

    @PostMapping
    public ResponseEntity<UniversityContactResponse> createContact(
            @Valid @RequestBody UniversityContactRequest request) {
        UniversityContactResponse response = contactService.createContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //get all contacts
    @GetMapping
    public ResponseEntity<List<UniversityContactResponse>> getAllContacts() {
        List<UniversityContactResponse> responses = contactService.getAllContacts();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityContactResponse> getContactById(@PathVariable Integer id) {
        UniversityContactResponse response = contactService.getContactById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<UniversityContactResponse>> getContactsByUniversityId(
            @PathVariable Integer universityId) {
        List<UniversityContactResponse> responses = contactService.getContactsByUniversityId(universityId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityContactResponse> updateContact(
            @PathVariable Integer id,
            @Valid @RequestBody UniversityContactRequest request) {
        UniversityContactResponse response = contactService.updateContact(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
