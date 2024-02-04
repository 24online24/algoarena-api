package org.judy.algoarena.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.problemset.ProblemSetCreateDTO;
import org.judy.algoarena.dto.problemset.ProblemSetResponseDTO;
import org.judy.algoarena.dto.problemset.ProblemSetUpdateDTO;
import org.judy.algoarena.mappers.ProblemSetMapper;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.ProblemSet;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.ProblemRepository;
import org.judy.algoarena.repositories.ProblemSetRepository;
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

@RestController
@RequestMapping("/problemsets")
public class ProblemSetController {
    private final ProblemSetRepository problemSetRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    public ProblemSetController(
            ProblemSetRepository problemSetRepository,
            UserRepository userRepository,
            ProblemRepository problemRepository) {
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    @PostMapping()
    public String addProblemSet(@RequestBody ProblemSetCreateDTO problemSetCreateDTO) {
        problemSetRepository.save(ProblemSetMapper.convertToEntity(problemSetCreateDTO));
        return "Added new problem set to repo!";
    }

    @GetMapping()
    public Iterable<ProblemSetResponseDTO> getProblemSets() {
        return StreamSupport.stream(problemSetRepository.findAll().spliterator(), true)
                .map(ProblemSetMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProblemSetResponseDTO findProblemSet(@PathVariable @NonNull Long id) {
        return ProblemSetMapper.convertToDTO(problemSetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Problem set not found with ID: " + id)));
    }

    @PutMapping("/{id}")
    public ProblemSetResponseDTO updateProblemSet(@RequestBody ProblemSetUpdateDTO problemSetUpdateDTO) {
        ProblemSet problemSet = problemSetRepository.findById(problemSetUpdateDTO.getId()).orElseThrow(
                () -> new IllegalArgumentException("Problem set not found with ID: " + problemSetUpdateDTO.getId()));
        User creator = userRepository.findById(problemSetUpdateDTO.getCreatorId()).orElseThrow(
                () -> new IllegalArgumentException("User not found with ID: " + problemSetUpdateDTO.getCreatorId()));
        List<Problem> problems = problemSetUpdateDTO.getProblemIds().stream().map(problemRepository::findById)
                .map(problem -> problem
                        .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + problem)))
                .toList();

        problemSet.setProblemSetName(problemSetUpdateDTO.getProblemSetName());
        problemSet.setCreator(creator);
        problemSet.setDescription(problemSetUpdateDTO.getDescription());
        problemSet.setProblems(problems);
        problemSetRepository.save(problemSet);
        return ProblemSetMapper.convertToDTO(problemSet);
    }

    @DeleteMapping("/{id}")
    public void deleteProblemSetById(@PathVariable @NonNull Long id) {
        problemSetRepository.deleteById(id);
    }
}
