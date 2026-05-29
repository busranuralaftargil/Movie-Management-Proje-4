package com.sau.mm.service;

import com.sau.mm.dto.CategoryDTO;
import com.sau.mm.model.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories();
    public CategoryDTO getCategoryById(Long id);
    public CategoryDTO createCategory(Category category);
    public CategoryDTO updateCategory(Long id, Category category);
    public void deleteCategory(Long id);
}