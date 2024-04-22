package za.ac.tut.kotashop.service;

import org.springframework.stereotype.Service;
import za.ac.tut.kotashop.dto.CategoryDto;
import za.ac.tut.kotashop.entity.Category;
import za.ac.tut.kotashop.repository.CategoryRepository;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToCategoryDto)
                .collect(Collectors.toList());
    }

    private CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
}
