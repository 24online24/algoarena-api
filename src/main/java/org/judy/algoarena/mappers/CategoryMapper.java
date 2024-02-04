package org.judy.algoarena.mappers;

import java.util.ArrayList;

import org.judy.algoarena.dto.category.CategoryCreateDTO;
import org.judy.algoarena.dto.category.CategoryResponseDTO;
import org.judy.algoarena.models.Category;
import org.judy.algoarena.models.Problem;

public class CategoryMapper {
    public static CategoryResponseDTO convertToDTO(Category category) {
        return new CategoryResponseDTO(category.getCategoryName());
    }

    public static Category convertToEntity(CategoryCreateDTO categoryCreateDTO) {
        return new Category(categoryCreateDTO.getCategoryName(), new ArrayList<Problem>());
    }
}
