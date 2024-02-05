package org.judy.algoarena.dto.problem;

import java.util.List;

import org.judy.algoarena.models.Difficulty;

import lombok.Getter;

@Getter
public class ProblemUpdateDTO extends ProblemCreateDTO {
  private Long id;

  public ProblemUpdateDTO(Long id, Long authorId, String name, String description, Difficulty difficulty,
      List<Long> categories) {
    super(authorId, name, description, difficulty, categories);
    this.id = id;
  }
}
