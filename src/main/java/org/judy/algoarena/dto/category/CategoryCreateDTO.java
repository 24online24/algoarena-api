package org.judy.algoarena.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateDTO extends CategoryDTO {
  public CategoryCreateDTO(String categoryName) {
    super(categoryName);
  }
}