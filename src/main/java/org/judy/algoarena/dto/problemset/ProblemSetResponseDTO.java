package org.judy.algoarena.dto.problemset;

import java.util.List;

import org.judy.algoarena.dto.problem.ProblemResponseDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;

import lombok.Getter;

@Getter
public class ProblemSetResponseDTO extends ProblemSetDTO {
  private Long id;
  private UserResponseDTO creator;
  private List<ProblemResponseDTO> problems;

  public ProblemSetResponseDTO(Long id, String problemSetName, UserResponseDTO creator, String description,
      List<ProblemResponseDTO> problems) {
    super(problemSetName, description);
    this.id = id;
    this.creator = creator;
    this.problems = problems;
  }
}
