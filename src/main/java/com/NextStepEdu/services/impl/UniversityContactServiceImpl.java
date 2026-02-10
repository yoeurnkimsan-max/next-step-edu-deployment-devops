package com.NextStepEdu.services.impl;

import com.NextStepEdu.dto.requests.UniversityContactRequest;
import com.NextStepEdu.dto.responses.UniversityContactResponse;
import com.NextStepEdu.mappers.UniversityContactMapper;
import com.NextStepEdu.models.UniversityContactModel;
import com.NextStepEdu.models.UniversityModel;
import com.NextStepEdu.repositories.UniversityContactRepository;
import com.NextStepEdu.repositories.UniversityRepository;
import com.NextStepEdu.services.UniversityContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityContactServiceImpl implements UniversityContactService {

    private final UniversityContactRepository contactRepository;
    private final UniversityRepository universityRepository;
    private final UniversityContactMapper contactMapper;

    @Override
    @Transactional
    public UniversityContactResponse createContact(UniversityContactRequest request) {
        UniversityModel university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "University not found with id: " + request.getUniversityId()));

        UniversityContactModel contact = contactMapper.toModel(request);
        contact.setUniversity(university);

        UniversityContactModel savedContact = contactRepository.save(contact);
        return contactMapper.toResponse(savedContact);
    }

    @Override
    public UniversityContactResponse getContactById(Integer id) {
        UniversityContactModel contact = contactRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));
        return contactMapper.toResponse(contact);
    }

    @Override
    public List<UniversityContactResponse> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UniversityContactResponse> getContactsByUniversityId(Integer universityId) {
        if (!universityRepository.existsById(universityId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found with id: " + universityId);
        }

        return contactRepository.findByUniversityId(universityId).stream()
                .map(contactMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UniversityContactResponse updateContact(Integer id, UniversityContactRequest request) {
        UniversityContactModel contact = contactRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));

        UniversityModel university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "University not found with id: " + request.getUniversityId()));

        contact.setLabel(request.getLabel());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setWebsiteUrl(request.getWebsiteUrl());
        contact.setUniversity(university);

        UniversityContactModel updatedContact = contactRepository.save(contact);
        return contactMapper.toResponse(updatedContact);
    }

    @Override
    @Transactional
    public void deleteContact(Integer id) {
        if (!contactRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}
