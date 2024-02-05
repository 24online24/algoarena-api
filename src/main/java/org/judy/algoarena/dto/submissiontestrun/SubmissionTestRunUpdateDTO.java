package org.judy.algoarena.dto.submissiontestrun;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.Getter;

@Getter
public class SubmissionTestRunUpdateDTO extends SubmissionTestRunCreateDTO {
    private Long id;

    public SubmissionTestRunUpdateDTO(Long id, Long submissionId, Long testcaseId, SubmissionStatus status) {
        super(submissionId, testcaseId, status);
        this.id = id;
    }
}
