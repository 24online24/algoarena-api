package org.judy.algoarena.dto.testcase;

import lombok.Getter;

@Getter
public class TestCaseUpdateDTO extends TestCaseCreateDTO {
    private Long id;

    public TestCaseUpdateDTO(Long id, Long problemId, String input, String output, Boolean isExample) {
        super(problemId, input, output, isExample);
        this.id = id;
    }
}
