package org.judy.algoarena.dto.problem;

import org.judy.algoarena.models.Difficulty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class ProblemDTO {
    @NotBlank
    private String name;
    private String description;
    private Difficulty difficulty;
    @NotBlank
    private String exampleInput;
    @NotBlank
    private String exampleOutput;
    @NotBlank
    private String input;
    @NotBlank
    private String output;
}
