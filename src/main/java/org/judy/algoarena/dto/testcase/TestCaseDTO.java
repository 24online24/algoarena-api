package org.judy.algoarena.dto.testcase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class TestCaseDTO {
    private String input;
    private String output;
    private Boolean isExample;
}
