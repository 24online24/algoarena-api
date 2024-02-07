package org.judy.algoarena.dto.problem;

import java.util.List;

import org.judy.algoarena.models.Difficulty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProblemCreateDTO extends ProblemDTO {
    @NotNull
    private Long authorId;
    private List<Long> categoriesIds;

    public ProblemCreateDTO(
            Long authorId,
            String name,
            String description,
            Difficulty difficulty,
            List<Long> categories,
            String exampleInput,
            String exampleOutput,
            String input,
            String output) {
        super(name, description, difficulty, exampleInput, exampleOutput, input, output);
        this.authorId = authorId;
        this.categoriesIds = categories;
        ;
    }
}
