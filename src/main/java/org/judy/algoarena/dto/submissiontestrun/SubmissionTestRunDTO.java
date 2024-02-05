package org.judy.algoarena.dto.submissiontestrun;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class SubmissionTestRunDTO {
    private SubmissionStatus status;
}
