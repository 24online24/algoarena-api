package org.judy.algoarena.dto.category;

import lombok.Getter;

@Getter
public class CategoryCreateDTO extends CategoryDTO {
  public CategoryCreateDTO(String categoryName) {
    super(categoryName);
  }
}