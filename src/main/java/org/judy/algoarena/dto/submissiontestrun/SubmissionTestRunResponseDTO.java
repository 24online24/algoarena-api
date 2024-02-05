package org.judy.algoarena.dto.submissiontestrun;

import org.judy.algoarena.dto.submission.SubmissionResponseDTO;
import org.judy.algoarena.dto.testcase.TestCaseResponseDTO;
import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionTestRunResponseDTO extends SubmissionTestRunDTO {
    private Long id;
    private SubmissionResponseDTO submission;
    private TestCaseResponseDTO testCase;

    public SubmissionTestRunResponseDTO(Long id, SubmissionResponseDTO submission, TestCaseResponseDTO testCase,
            SubmissionStatus status) {
        super(status);
        this.id = id;
        this.submission = submission;
        this.testCase = testCase;
    }
}
