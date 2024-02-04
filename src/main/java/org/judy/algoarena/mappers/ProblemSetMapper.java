package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.problemset.ProblemSetCreateDTO;
import org.judy.algoarena.dto.problemset.ProblemSetResponseDTO;
import org.judy.algoarena.models.ProblemSet;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.ProblemRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;

public class ProblemSetMapper {
    private static UserRepository userRepository;
    private static ProblemRepository problemRepository;

    public ProblemSetMapper(UserRepository userRepository, ProblemRepository problemRepository) {
        ProblemSetMapper.userRepository = userRepository;
        ProblemSetMapper.problemRepository = problemRepository;
    }

    public static ProblemSetResponseDTO convertToDTO(ProblemSet problemSet) {
        return new ProblemSetResponseDTO(
                problemSet.getId(),
                problemSet.getProblemSetName(),
                UserMapper.convertToDTO(problemSet.getCreator()),
                problemSet.getDescription(),
                problemSet.getProblems().stream()
                        .map(ProblemMapper::convertToDTO)
                        .toList());
    }

    @NonNull
    public static ProblemSet convertToEntity(ProblemSetCreateDTO problemSetCreateDTO) {
        Long creatorId = problemSetCreateDTO.getCreatorId();
        if (creatorId == null) {
            throw new IllegalArgumentException("Creator ID cannot be null");
        }
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("Creator not found with ID: " + creatorId));

        return new ProblemSet(
                problemSetCreateDTO.getProblemSetName(),
                creator,
                problemSetCreateDTO.getDescription(),
                problemSetCreateDTO.getProblemIds().stream()
                        .map(problemRepository::findById)
                        .map(problem -> problem.orElseThrow(() -> new IllegalArgumentException(
                                "Problem not found with ID: " + problem.get().getId())))
                        .toList());
    }
}
