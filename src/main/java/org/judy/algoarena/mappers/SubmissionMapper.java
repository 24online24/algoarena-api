package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.submission.SubmissionCreateDTO;
import org.judy.algoarena.dto.submission.SubmissionResponseDTO;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.Submission;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.ProblemRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;

public class SubmissionMapper {
    private static UserRepository userRepository;
    private static ProblemRepository problemRepository;

    public SubmissionMapper(
            UserRepository userRepository,
            ProblemRepository problemRepository) {
        SubmissionMapper.userRepository = userRepository;
        SubmissionMapper.problemRepository = problemRepository;
    }

    public static SubmissionResponseDTO convertToDTO(Submission submission) {
        return new SubmissionResponseDTO(
                submission.getId(),
                UserMapper.convertToDTO(submission.getAuthor()),
                ProblemMapper.convertToDTO(submission.getProblem()),
                submission.getCode(),
                submission.getLanguage_id(),
                submission.getStatus());
    }

    @NonNull
    public static Submission convertToEntity(SubmissionCreateDTO submissionCreateDTO) {
        Long authorId = submissionCreateDTO.getAuthorId();
        if (authorId == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Author not found with ID: " + authorId));

        Long problemId = submissionCreateDTO.getProblemId();
        if (problemId == null) {
            throw new IllegalArgumentException("Problem ID cannot be null");
        }
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Problem not found with ID: " + problemId));

        return new Submission(
                author,
                problem,
                submissionCreateDTO.getCode(),
                submissionCreateDTO.getLanguage_id(),
                submissionCreateDTO.getStatus());
    }
}
