package org.judy.algoarena.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.problem.ProblemCreateDTO;
import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.dto.problem.ProblemUpdateDTO;
import org.judy.algoarena.mappers.ProblemMapper;
import org.judy.algoarena.models.Category;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.CategoryRepository;
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
@RequestMapping("/problems")
public class ProblemController {

        private final ProblemRepository problemRepository;
        private final UserRepository userRepository;
        private final CategoryRepository categoryRepository;

        public ProblemController(
                        ProblemRepository problemRepository,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository,
                        ProblemSetRepository problemSetRepository) {
                this.problemRepository = problemRepository;
                this.userRepository = userRepository;
                this.categoryRepository = categoryRepository;
        }

        @PostMapping()
        public String addProblem(@RequestBody ProblemCreateDTO problemCreateDTO) {
                User author = userRepository.findById(problemCreateDTO.getAuthorId()).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "Author not found with ID: " + problemCreateDTO.getAuthorId()));
                List<Category> categories = problemCreateDTO.getCategoriesIds().stream()
                                .map(categoryRepository::findById)
                                .map(category -> category.orElseThrow(
                                                () -> new IllegalArgumentException("Category not found with ID: "
                                                                + category.get().getId())))
                                .toList();

                Problem problem = new Problem();
                problem.setAuthor(author);
                problem.setName(problemCreateDTO.getName());
                problem.setDescription(problemCreateDTO.getDescription());
                problem.setDifficulty(problemCreateDTO.getDifficulty());
                problem.setCategories(categories);
                problem.setProblemSets(new ArrayList<>());
                problemRepository.save(problem);
                return "Added new problem to repo!";
        }

        @GetMapping()
        public Iterable<ProblemResponseDTO> getProblems() {
                return StreamSupport.stream(problemRepository.findAll().spliterator(), true)
                                .map(ProblemMapper::convertToDTO)
                                .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public ProblemResponseDTO findProblemById(@PathVariable @NonNull Long id) {
                return ProblemMapper.convertToDTO(problemRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + id)));
        }

        @PutMapping("/{id}")
        public ProblemResponseDTO updateProblem(@RequestBody ProblemUpdateDTO problemUpdateDTO) {
                Problem problem = problemRepository.findById(problemUpdateDTO.getId()).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "Problem not found with ID: " + problemUpdateDTO.getId()));
                User author = userRepository.findById(problemUpdateDTO.getAuthorId()).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "Author not found with ID: " + problemUpdateDTO.getAuthorId()));
                List<Category> categories = problemUpdateDTO.getCategoriesIds().stream()
                                .map(categoryRepository::findById)
                                .map(category -> category.orElseThrow(
                                                () -> new IllegalArgumentException("Category not found with ID: "
                                                                + category.get().getId())))
                                .toList();

                problem.setAuthor(author);
                problem.setName(problemUpdateDTO.getName());
                problem.setDescription(problemUpdateDTO.getDescription());
                problem.setDifficulty(problemUpdateDTO.getDifficulty());
                // Donno if this is optimal
                problem.getCategories().clear();
                problem.getCategories().addAll(categories);
                problemRepository.save(problem);
                return ProblemMapper.convertToDTO(problem);
        }

        @DeleteMapping("/{id}")
        public void deleteProblem(@PathVariable @NonNull Long id) {
                problemRepository.deleteById(id);
        }
}
