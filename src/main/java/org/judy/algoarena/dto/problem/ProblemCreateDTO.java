package org.judy.algoarena.dto.problem;

import java.util.List;

import org.judy.algoarena.models.Difficulty;

import lombok.Getter;

@Getter
public class ProblemCreateDTO extends ProblemDTO {
  private Long authorId;
  private List<Long> categories;
  private List<Long> problemSets;

  public ProblemCreateDTO(Long authorId, String name, String description, Difficulty difficulty, List<Long> categories,
      List<Long> problemSets) {
    super(name, description, difficulty);
    this.authorId = authorId;
    this.categories = categories;
    this.problemSets = problemSets;
  }
}
