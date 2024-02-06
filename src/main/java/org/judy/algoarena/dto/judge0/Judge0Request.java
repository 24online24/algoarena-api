package org.judy.algoarena.dto.judge0;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Judge0Request {
    private String source_code;
    private int language_id;
    private String stdin;
    private String expected_output;
}
