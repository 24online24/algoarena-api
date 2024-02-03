package org.judy.algoarena.dto.category;

import lombok.Getter;

@Getter
public class CategoryUpdateDTO extends CategoryCreateDTO {
  private Long id;

  public CategoryUpdateDTO(Long id, String categoryName) {
    super(categoryName);
    this.id = id;
  }
}
