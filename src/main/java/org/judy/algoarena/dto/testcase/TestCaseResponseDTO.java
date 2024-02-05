package org.judy.algoarena.dto.testcase;

import org.judy.algoarena.dto.problem.ProblemResponseDTO;

import lombok.Getter;

@Getter
public class TestCaseResponseDTO extends TestCaseDTO {
    private Long id;
    private ProblemResponseDTO problem;

    public TestCaseResponseDTO(Long id, ProblemResponseDTO problem, String input, String output, Boolean isExample) {
        super(input, output, isExample);
    }
}
