package org.judy.algoarena.dto.submission;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SubmissionCreateDTO extends SubmissionDTO {
    @NotEmpty
    private Long authorId;
    @NotEmpty
    private Long problemId;

    public SubmissionCreateDTO(Long authorId, Long problemId, String code, int language_id) {
        super(code, language_id);
        this.authorId = authorId;
        this.problemId = problemId;
    }
}
