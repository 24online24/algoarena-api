package org.judy.algoarena.dto.problemset;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class ProblemSetDTO {
  private String problemSetName;
  private String description;
}
