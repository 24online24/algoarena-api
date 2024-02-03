package org.judy.algoarena.dto.problemset;

import lombok.Getter;

@Getter
public class ProblemSetCreateDTO extends ProblemSetDTO {
  private Long creatorId;

  public ProblemSetCreateDTO(String problemSetName, Long creatorId, String description) {
    super(problemSetName, description);
    this.creatorId = creatorId;
  }
}
