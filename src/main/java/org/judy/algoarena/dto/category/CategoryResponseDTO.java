package org.judy.algoarena.dto.category;

import lombok.Getter;

@Getter
public class CategoryResponseDTO extends CategoryDTO {
  private Long id;

  public CategoryResponseDTO(Long id, String categoryName) {
    super(categoryName);
    this.id = id;
  }
}
