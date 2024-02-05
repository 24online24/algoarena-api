package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.category.CategoryCreateDTO;
import org.judy.algoarena.dto.category.CategoryResponseDTO;
import org.judy.algoarena.models.Category;
import org.springframework.lang.NonNull;

public class CategoryMapper {
    public static CategoryResponseDTO convertToDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getCategoryName());
    }

    @NonNull
    public static Category convertToEntity(CategoryCreateDTO categoryCreateDTO) {
        return new Category(categoryCreateDTO.getCategoryName());
    }
}
