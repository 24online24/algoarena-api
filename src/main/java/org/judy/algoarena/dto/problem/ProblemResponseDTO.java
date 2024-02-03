package org.judy.algoarena.dto.problem;

import java.util.List;

import org.judy.algoarena.dto.category.CategoryResponseDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.models.Difficulty;

import lombok.Getter;

@Getter
public class ProblemResponseDTO extends ProblemDTO {
  private UserResponseDTO author;
  private List<CategoryResponseDTO> categories;

  public ProblemResponseDTO(UserResponseDTO author, String name, String description, Difficulty difficulty,
      List<CategoryResponseDTO> categories) {
    super(name, description, difficulty);
    this.author = author;
    this.categories = categories;
  }
}
