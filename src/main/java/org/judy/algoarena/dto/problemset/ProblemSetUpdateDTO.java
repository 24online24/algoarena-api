package org.judy.algoarena.dto.problemset;

import java.util.List;

import lombok.Getter;

@Getter
public class ProblemSetUpdateDTO extends ProblemSetCreateDTO {
  private Long id;

  public ProblemSetUpdateDTO(Long id, String problemSetName, Long creatorId, String description, List<Long> problemIds) {
    super(problemSetName, creatorId, description, problemIds);
    this.id = id;
  }
}
