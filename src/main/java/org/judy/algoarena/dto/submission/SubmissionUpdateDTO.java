package org.judy.algoarena.dto.submission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SubmissionUpdateDTO extends SubmissionCreateDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String status;

    public SubmissionUpdateDTO(Long id, Long authorId, Long problemId, String code, int language_id,
            String status) {
        super(authorId, problemId, code, language_id);
        this.status = status;
    }
}
