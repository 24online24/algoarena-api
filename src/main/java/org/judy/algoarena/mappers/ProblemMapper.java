package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.problem.ProblemCreateDTO;
import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.CategoryRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;

public class ProblemMapper {
    private static UserRepository userRepository;
    private static CategoryRepository categoryRepository;

    public ProblemMapper(
            UserRepository userRepository,
            CategoryRepository categoryRepository) {
        ProblemMapper.userRepository = userRepository;
        ProblemMapper.categoryRepository = categoryRepository;
    }

    public static ProblemResponseDTO convertToDTO(Problem problem) {
        return new ProblemResponseDTO(
                problem.getId(),
                UserMapper.convertToDTO(problem.getAuthor()),
                problem.getName(),
                problem.getDescription(),
                problem.getDifficulty(),
                problem.getCategories().stream()
                        .map(CategoryMapper::convertToDTO)
                        .toList(),
                problem.getExampleInput(),
                problem.getExampleOutput(),
                problem.getInput(),
                problem.getOutput());
    }

    @NonNull
    public static Problem convertToEntity(ProblemCreateDTO problemCreateDTO) {
        Long authorId = problemCreateDTO.getAuthorId();
        if (authorId == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Author not found with ID: " + authorId));

        return new Problem(
                author,
                problemCreateDTO.getName(),
                problemCreateDTO.getDescription(),
                problemCreateDTO.getDifficulty(),
                problemCreateDTO.getCategoriesIds().stream()
                        .map(categoryRepository::findById)
                        .map(category -> category
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "Category not found with ID: "
                                                + category.get().getId())))
                        .toList(),
                problemCreateDTO.getExampleInput(),
                problemCreateDTO.getExampleOutput(),
                problemCreateDTO.getInput(),
                problemCreateDTO.getOutput());
    }
}
