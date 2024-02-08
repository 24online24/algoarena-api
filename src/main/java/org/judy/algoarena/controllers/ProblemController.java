package org.judy.algoarena.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.problem.ProblemCreateDTO;
import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.dto.problem.ProblemUpdateDTO;
import org.judy.algoarena.mappers.ProblemMapper;
import org.judy.algoarena.models.Category;
import org.judy.algoarena.models.Difficulty;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.Submission;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.CategoryRepository;
import org.judy.algoarena.repositories.ProblemRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                        CategoryRepository categoryRepository) {
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

                if (categories.isEmpty())
                        throw new IllegalArgumentException(
                                        "Categories not found with IDs: " + problemCreateDTO.getCategoriesIds());

                if (problemCreateDTO.getExampleInput().isEmpty() || problemCreateDTO.getExampleOutput().isEmpty()
                                || problemCreateDTO.getInput().isEmpty() || problemCreateDTO.getOutput().isEmpty())
                        throw new IllegalArgumentException(
                                        "Example input, example output, input and output cannot be empty");
                if (problemCreateDTO.getDifficulty() != Difficulty.EASY &&
                                problemCreateDTO.getDifficulty() != Difficulty.MEDIUM &&
                                problemCreateDTO.getDifficulty() != Difficulty.HARD)
                        throw new IllegalArgumentException(
                                        "Difficulty must be EASY, MEDIUM or HARD");
                if (problemCreateDTO.getName().isEmpty() || problemCreateDTO.getDescription().isEmpty())
                        throw new IllegalArgumentException(
                                        "Name and description cannot be empty");

                Problem problem = new Problem(
                                author,
                                problemCreateDTO.getName(),
                                problemCreateDTO.getDescription(),
                                problemCreateDTO.getDifficulty(),
                                categories,
                                problemCreateDTO.getExampleInput(),
                                problemCreateDTO.getExampleOutput(),
                                problemCreateDTO.getInput(),
                                problemCreateDTO.getOutput());

                problemRepository.save(problem);
                return "Added new problem to repo!";
        }

        @GetMapping()
        public Iterable<ProblemResponseDTO> getProblems() {
                return StreamSupport.stream(problemRepository.findAll().spliterator(), false)
                                .map(ProblemMapper::convertToDTO)
                                .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public ProblemResponseDTO findProblemById(@PathVariable @NonNull Long id) {
                return ProblemMapper.convertToDTO(problemRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + id)));
        }

        @GetMapping("/random")
        public Iterable<ProblemResponseDTO> getRandomProblems() {
                List<Problem> problems = StreamSupport.stream(problemRepository.findAll().spliterator(), false)
                                .collect(Collectors.toList());
                Collections.shuffle(problems);
                return problems.stream().limit(9).map(ProblemMapper::convertToDTO).collect(Collectors.toList());
        }

        @GetMapping("/{id}/status")
        public String getStatus(@PathVariable @NonNull Long id, @RequestParam Long userId) {
                Problem problem = problemRepository.findById(id).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "Problem not found with ID: " + id));
                User user = userRepository.findById(userId).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "User not found with ID: " + userId));
                Submission submissions = problem.getSubmissions().stream()
                                .filter(submission -> submission.getAuthor().equals(user)
                                                && submission.getProblem().equals(problem)
                                                && submission.getStatus() == "Accepted")
                                .findFirst()
                                .orElse(null);
                if (submissions != null)
                        return "Solved";
                return "Unsolved";
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
                // set the category of all problems to null
                Problem problem = problemRepository.findById(id).orElseThrow(
                                () -> new IllegalArgumentException(
                                                "Problem not found with ID: " + id));
                problem.getCategories().clear();
                problemRepository.save(problem);
                problemRepository.deleteById(id);
        }
}
