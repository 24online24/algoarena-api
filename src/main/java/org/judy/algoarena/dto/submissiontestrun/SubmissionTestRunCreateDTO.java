package org.judy.algoarena.dto.submissiontestrun;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionTestRunCreateDTO extends SubmissionTestRunDTO {
    private Long submissionId;
    private Long testCaseId;

    public SubmissionTestRunCreateDTO(Long submissionId, Long testcaseId, SubmissionStatus status) {
        super(status);
        this.submissionId = submissionId;
        this.testCaseId = testcaseId;
    }
}
