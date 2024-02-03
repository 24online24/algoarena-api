package org.judy.algoarena.dto.problemset;

import lombok.Getter;

@Getter
public class ProblemSetUpdateDTO extends ProblemSetCreateDTO {
  private Long id;

  public ProblemSetUpdateDTO(Long id, String problemSetName, Long creatorId, String description) {
    super(problemSetName, creatorId, description);
    this.id = id;
  }
}
