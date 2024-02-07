package org.judy.algoarena.dto.submission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class SubmissionDTO {
    @NotBlank
    private String code;
    @NotNull
    private int language_id;
}
