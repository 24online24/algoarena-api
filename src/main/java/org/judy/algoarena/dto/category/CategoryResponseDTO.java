package org.judy.algoarena.dto.category;

import lombok.Getter;

@Getter
public class CategoryResponseDTO extends CategoryDTO {
  public CategoryResponseDTO(String categoryName) {
    super(categoryName);
  }
}
