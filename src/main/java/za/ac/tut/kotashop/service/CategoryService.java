package za.ac.tut.kotashop.service;

import za.ac.tut.kotashop.dto.CategoryDto;


import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDto categoryDto);
    void deleteCategory(Long categoryId);
    List<CategoryDto> findAllCategories();
}
