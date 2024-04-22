package za.ac.tut.kotashop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private Long categoryId;

    @NotEmpty(message = "Category name should not be empty")
    @Size(min = 3, message = "Category name should have minimum 3 or more characters")
    private String categoryName;

    // Getters and setters

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
