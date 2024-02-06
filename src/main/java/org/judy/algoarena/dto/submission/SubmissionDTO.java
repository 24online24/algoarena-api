package org.judy.algoarena.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class SubmissionDTO {
    private String code;
    private int language_id;
}
