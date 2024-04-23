package za.ac.tut.kotashop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Category;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.repository.CategoryRepository;
import za.ac.tut.kotashop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveProduct(ProductDto productDto , MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty.");
        }
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());

        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        Category category = categoryOptional.orElseThrow(() -> new IllegalArgumentException("Category not found"));
        product.setCategory(category);
        try {
            product.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            // Log the error and rethrow it as a runtime exception
            throw new RuntimeException("Failed to read file content", e);
        }
        // Save the product to the database
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public void editProduct(ProductDto productDto) {

    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
        return productDtos;
    }

    // Helper method to map Product entity to ProductDto
    private ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        return productDto;
    }
}
