package org.judy.algoarena.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.submission.SubmissionCreateDTO;
import org.judy.algoarena.dto.submission.SubmissionResponseDTO;
import org.judy.algoarena.dto.submission.SubmissionUpdateDTO;
import org.judy.algoarena.mappers.SubmissionMapper;
import org.judy.algoarena.models.Submission;
import org.judy.algoarena.repositories.SubmissionRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionRepository submissionRepository;

    public SubmissionController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @PostMapping()
    public String addSubmission(@RequestBody SubmissionCreateDTO submissionCreateDTO) {
        submissionRepository.save(SubmissionMapper.convertToEntity(submissionCreateDTO));
        return "Added new submission to repo!";
    }

    @GetMapping()
    public Iterable<SubmissionResponseDTO> getSubmissions() {
        return StreamSupport.stream(submissionRepository.findAll().spliterator(), true)
                .map(SubmissionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SubmissionResponseDTO findSubmissionById(@PathVariable @NonNull Long id) {
        return SubmissionMapper.convertToDTO(submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found with ID: " + id)));
    }

    @PutMapping("/{id}")
    public SubmissionResponseDTO updateSubmission(@RequestBody SubmissionUpdateDTO submissionUpdateDTO) {
        Submission submission = submissionRepository.findById(submissionUpdateDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Submission not found with ID: " + submissionUpdateDTO.getId()));

        submission.setStatus(submissionUpdateDTO.getStatus());
        submissionRepository.save(submission);
        return SubmissionMapper.convertToDTO(submission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable @NonNull Long id) {
        submissionRepository.deleteById(id);
    }
}
