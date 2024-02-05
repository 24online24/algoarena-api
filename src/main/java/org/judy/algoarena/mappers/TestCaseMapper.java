package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.testcase.TestCaseCreateDTO;
import org.judy.algoarena.dto.testcase.TestCaseResponseDTO;
import org.judy.algoarena.models.Problem;
import org.judy.algoarena.models.TestCase;
import org.judy.algoarena.repositories.ProblemRepository;
import org.springframework.lang.NonNull;

public class TestCaseMapper {
    private static ProblemRepository problemRepository;

    public TestCaseMapper(ProblemRepository problemRepository) {
        TestCaseMapper.problemRepository = problemRepository;
    }

    public static TestCaseResponseDTO convertToDTO(TestCase testCase) {
        return new TestCaseResponseDTO(
                testCase.getId(),
                ProblemMapper.convertToDTO(testCase.getProblem()),
                testCase.getInput(),
                testCase.getOutput(),
                testCase.getIsExample());
    }

    @NonNull
    public static TestCase convertToEntity(TestCaseCreateDTO testCaseResponseDTO) {
        Long problemId = testCaseResponseDTO.getProblemId();
        if (problemId == null) {
            throw new IllegalArgumentException("Problem ID cannot be null");
        }
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Problem not found with ID: " + problemId));

        return new TestCase(
                problem,
                testCaseResponseDTO.getInput(),
                testCaseResponseDTO.getOutput(),
                testCaseResponseDTO.getIsExample());
    }
}
