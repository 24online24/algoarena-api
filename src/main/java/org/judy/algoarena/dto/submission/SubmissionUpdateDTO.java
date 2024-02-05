package org.judy.algoarena.dto.submission;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionUpdateDTO extends SubmissionCreateDTO {
    private Long id;

    public SubmissionUpdateDTO(Long id, Long authorId, Long problemId, String code, int language_id,
            SubmissionStatus status) {
        super(authorId, problemId, code, language_id, status);
        this.id = id;
    }
}
