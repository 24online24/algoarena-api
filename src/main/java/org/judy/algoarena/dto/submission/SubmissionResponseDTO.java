package org.judy.algoarena.dto.submission;

import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;

import lombok.Getter;

@Getter
public class SubmissionResponseDTO extends SubmissionDTO {
    private Long id;
    private UserResponseDTO author;
    private ProblemResponseDTO problem;
    private String status;
    private String message;
    private String time;
    private String memory;

    public SubmissionResponseDTO(
            Long id,
            UserResponseDTO author,
            ProblemResponseDTO problem,
            String code,
            int language_id,
            String status,
            String message,
            String time,
            String memory) {
        super(code, language_id);
        this.id = id;
        this.author = author;
        this.problem = problem;
        this.status = status;
        this.message = message;
        this.time = time;
        this.memory = memory;
    }
}
