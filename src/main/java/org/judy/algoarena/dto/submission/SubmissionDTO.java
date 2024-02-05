package org.judy.algoarena.dto.submission;

import org.judy.algoarena.models.SubmissionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class SubmissionDTO {
    private String code;
    private int language_id;
    private SubmissionStatus status;
}
