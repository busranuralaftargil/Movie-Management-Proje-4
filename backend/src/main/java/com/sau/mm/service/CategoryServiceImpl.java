package com.sau.mm.service;

import com.sau.mm.dto.CategoryDTO;
import com.sau.mm.exception.ErrorMessages;
import com.sau.mm.exception.ResourceAlreadyExistsException;
import com.sau.mm.exception.ResourceNotFoundException;
import com.sau.mm.model.Category;
import com.sau.mm.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CATEGORY_NOT_FOUND + ": " + id)).viewAsCategoryDTO();
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::viewAsCategoryDTO).toList();
    }

    public CategoryDTO createCategory(Category category) {
        if (categoryRepository.findById(category.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_CATEGORY_ALREADY_EXIST + ": " + category.getId());
        }
        return categoryRepository.save(category).viewAsCategoryDTO();
    }

    public CategoryDTO updateCategory(Long id, Category category) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CATEGORY_NOT_FOUND + ": " + id));
        category.setId(id);
        return categoryRepository.save(category).viewAsCategoryDTO();
    }

    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CATEGORY_NOT_FOUND + ": " + id));
        categoryRepository.deleteById(id);
    }
}