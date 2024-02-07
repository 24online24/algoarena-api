package org.judy.algoarena.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.judge0.Judge0Request;
import org.judy.algoarena.dto.submission.SubmissionCreateDTO;
import org.judy.algoarena.dto.submission.SubmissionResponseDTO;
import org.judy.algoarena.dto.submission.SubmissionUpdateDTO;
import org.judy.algoarena.mappers.SubmissionMapper;
import org.judy.algoarena.models.Submission;
import org.judy.algoarena.repositories.ProblemRepository;
import org.judy.algoarena.repositories.SubmissionRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;

    public SubmissionController(
            UserRepository userRepository,
            SubmissionRepository submissionRepository, ProblemRepository problemRepository) {
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;

    }

    @PostMapping()
    public Object addSubmission(@RequestBody SubmissionCreateDTO submissionCreateDTO) {
        Submission submission = new Submission(
                userRepository.findById(submissionCreateDTO.getAuthorId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Author not found with ID: "
                                        + submissionCreateDTO
                                                .getAuthorId())),
                problemRepository.findById(submissionCreateDTO.getProblemId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Problem not found with ID: "
                                        + submissionCreateDTO
                                                .getProblemId())),
                submissionCreateDTO.getCode(),
                submissionCreateDTO.getLanguage_id(),
                java.time.LocalDateTime.now());
        try {

            Judge0Request request = new Judge0Request();
            request.setSource_code(submission.getCode());
            request.setLanguage_id(submission.getLanguage_id());
            request.setStdin(problemRepository.findById(submissionCreateDTO.getProblemId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Problem not found with ID: "
                                    + submissionCreateDTO.getProblemId()))
                    .getInput());
            request.setExpected_output(problemRepository.findById(submissionCreateDTO.getProblemId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Problem not found with ID: "
                                    + submissionCreateDTO.getProblemId()))
                    .getOutput());

            // Create connection
            URL url = new URL("http://localhost:2358/submissions?wait=true&base64_encoded=false");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Write request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = new ObjectMapper().writeValueAsString(request)
                        .getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Check if response status is 2xx (success)
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                    // convert response to Judge0Response object
                    // save submission to database after getting status
                    JsonNode jsonObject = new ObjectMapper().readTree(response.toString());
                    String status = jsonObject.get("status").get("description").asText();
                    String message = jsonObject.get("message").asText();
                    String time = jsonObject.get("time").asText();
                    String memory = jsonObject.get("memory").asText();
                    submission.setStatus(status);
                    submission.setMessage(message);
                    submission.setTime(time);
                    submission.setMemory(memory);
                    submissionRepository.save(submission);

                    return submission.getStatus();
                } else {
                    submission.setStatus("Error");
                    submission.setMessage("Code execution failed.");
                    submission.setTime("0");
                    submission.setMemory("0");
                    submissionRepository.save(submission);
                    return submission.getStatus();
                }
            }
        } catch (Exception e) {
            submission.setStatus("Error");
            submission.setMessage("Code execution failed.");
            submission.setTime("0");
            submission.setMemory("0");
            submissionRepository.save(submission);
            return submission.getStatus();
        }
    }

    @GetMapping()
    public Iterable<SubmissionResponseDTO> getSubmissions() {
        return StreamSupport.stream(submissionRepository.findAll().spliterator(), false)
                .map(SubmissionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SubmissionResponseDTO findSubmissionById(@PathVariable @NonNull Long id) {
        return SubmissionMapper.convertToDTO(submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Submission not found with ID: " + id)));
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
