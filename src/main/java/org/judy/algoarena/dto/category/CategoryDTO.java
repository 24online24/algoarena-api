package org.judy.algoarena.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
abstract class CategoryDTO {
  @NotBlank
  private String categoryName;
}
