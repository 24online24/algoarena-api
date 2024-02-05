package org.judy.algoarena.dto.submission;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionCreateDTO extends SubmissionDTO {
    private Long authorId;
    private Long problemId;

    public SubmissionCreateDTO(Long authorId, Long problemId, String code, int language_id, SubmissionStatus status) {
        super(code, language_id, status);
        this.authorId = authorId;
        this.problemId = problemId;
    }
}
