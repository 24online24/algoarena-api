package org.judy.algoarena.dto.problemset;

import java.util.List;

import lombok.Getter;

@Getter
public class ProblemSetCreateDTO extends ProblemSetDTO {
  private Long creatorId;
  private List<Long> problemIds;

  public ProblemSetCreateDTO(String problemSetName, Long creatorId, String description, List<Long> problemIds) {
    super(problemSetName, description);
    this.creatorId = creatorId;
    this.problemIds = problemIds;
  }
}
