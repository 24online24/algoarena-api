package org.judy.algoarena.dto.submission;

import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionResponseDTO extends SubmissionDTO {
    private Long id;
    private UserResponseDTO author;
    private ProblemResponseDTO problem;

    public SubmissionResponseDTO(Long id, UserResponseDTO author, ProblemResponseDTO problem, String code,
            int language_id, SubmissionStatus status) {
        super(code, language_id, status);
        this.id = id;
        this.author = author;
        this.problem = problem;
    }
}
