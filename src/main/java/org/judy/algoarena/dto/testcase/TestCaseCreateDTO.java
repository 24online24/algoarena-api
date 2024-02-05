package org.judy.algoarena.dto.testcase;

import lombok.Getter;

@Getter
public class TestCaseCreateDTO extends TestCaseDTO {
    private Long problemId;

    public TestCaseCreateDTO(Long problemId, String input, String output, Boolean isExample) {
        super(input, output, isExample);
        this.problemId = problemId;
    }

}
