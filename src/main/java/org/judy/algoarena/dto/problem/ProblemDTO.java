package org.judy.algoarena.dto.problem;

import org.judy.algoarena.models.Difficulty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class ProblemDTO {
  private String name;
  private String description;
  private Difficulty difficulty;
}
