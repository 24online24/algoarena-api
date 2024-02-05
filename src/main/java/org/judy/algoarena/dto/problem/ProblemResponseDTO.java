package org.judy.algoarena.dto.problem;

import java.util.List;

import org.judy.algoarena.dto.category.CategoryResponseDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.models.Difficulty;

import lombok.Getter;

@Getter
public class ProblemResponseDTO extends ProblemDTO {
    Long id;
    private UserResponseDTO author;
    private List<CategoryResponseDTO> categories;

    public ProblemResponseDTO(
            Long id,
            UserResponseDTO author,
            String name,
            String description,
            Difficulty difficulty,
            List<CategoryResponseDTO> categories,
            String exampleInput,
            String exampleOutput,
            String input,
            String output) {
        super(name, description, difficulty, exampleInput, exampleOutput, input, output);
        this.id = id;
        this.author = author;
        this.categories = categories;
    }
}
