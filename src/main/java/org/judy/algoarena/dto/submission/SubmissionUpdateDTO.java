package org.judy.algoarena.dto.submission;

import lombok.Getter;

@Getter
public class SubmissionUpdateDTO extends SubmissionCreateDTO {
    private Long id;
    private String status;

    public SubmissionUpdateDTO(Long id, Long authorId, Long problemId, String code, int language_id,
            String status) {
        super(authorId, problemId, code, language_id);
        this.status = status;
    }
}
