package org.judy.algoarena.dto.submission;

import lombok.Getter;

@Getter
public class SubmissionCreateDTO extends SubmissionDTO {
    private Long authorId;
    private Long problemId;

    public SubmissionCreateDTO(Long authorId, Long problemId, String code, int language_id) {
        super(code, language_id);
        this.authorId = authorId;
        this.problemId = problemId;
    }
}
