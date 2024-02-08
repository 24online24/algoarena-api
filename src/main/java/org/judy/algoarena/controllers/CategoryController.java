package org.judy.algoarena.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.category.CategoryCreateDTO;
import org.judy.algoarena.dto.category.CategoryResponseDTO;
import org.judy.algoarena.dto.category.CategoryUpdateDTO;
import org.judy.algoarena.mappers.CategoryMapper;
import org.judy.algoarena.models.Category;
import org.judy.algoarena.repositories.CategoryRepository;
import org.judy.algoarena.repositories.ProblemRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final ProblemRepository problemRepository;

    public CategoryController(CategoryRepository categoryRepository, ProblemRepository problemRepository) {
        this.categoryRepository = categoryRepository;
        this.problemRepository = problemRepository;
    }

    @PostMapping()
    public String addCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        categoryRepository.save(CategoryMapper.convertToEntity(categoryCreateDTO));
        return "Added new category to repo!";
    }

    @GetMapping()
    public Iterable<CategoryResponseDTO> getCategories() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), true)
                .map(CategoryMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findCategoryyId(@PathVariable Long id) {
        return CategoryMapper.convertToDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@RequestBody @NonNull CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findById(categoryUpdateDTO.getId()).orElseThrow(
                () -> new IllegalArgumentException("Category not found with ID: " + categoryUpdateDTO.getId()));

        category.setCategoryName(categoryUpdateDTO.getCategoryName());
        categoryRepository.save(category);
        return CategoryMapper.convertToDTO(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable @NonNull Long id) {
        // iterate all problems and remove category from them
        problemRepository.findAll().forEach(problem -> {
            problem.getCategories().removeIf(category -> category.getId().equals(id));
            problemRepository.save(problem);
        });
        categoryRepository.deleteById(id); // this fails cause of many to many relationship
    }
}
