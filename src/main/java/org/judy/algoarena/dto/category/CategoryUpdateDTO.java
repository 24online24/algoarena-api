package org.judy.algoarena.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryUpdateDTO extends CategoryCreateDTO {
  @NotNull
  private Long id;

  public CategoryUpdateDTO(Long id, String categoryName) {
    super(categoryName);
    this.id = id;
  }
}
