package za.ac.tut.kotashop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.tut.kotashop.config.StorageProperty;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Category;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.repository.CategoryRepository;
import za.ac.tut.kotashop.repository.ProductRepository;
import za.ac.tut.kotashop.utils.PasswordEncryptor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private Path uploadsLocation;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryRepository categoryRepository1, StorageProperty storageProperty) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository1;
        this.uploadsLocation = Paths.get(storageProperty.getUploadsLocation());
    }

    @Override
    public void saveProduct(ProductDto productDto , MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty.");
        }

        String imageName = file.getOriginalFilename();
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductImage(imageName);
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        Category category = categoryOptional.orElseThrow(() -> new IllegalArgumentException("Category not found"));
        product.setCategory(category);
        Product saved = productRepository.save(product);
        String savedID = String.valueOf(saved.getProductId());
        Path newPath = uploadsLocation.resolve(savedID);
        if(!Files.exists(newPath)){
            try {
                Files.createDirectories(newPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Path destination = newPath.resolve(Paths.get(Objects.requireNonNull(imageName))).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void editProduct(ProductDto productDto) {

    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<Product> getAllProductsWithoutImage() {
        return productRepository.findProductsWithoutImage();
    }

    // Helper method to map Product entity to ProductDto
    private ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductImage(product.getProductImage());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        return productDto;
    }
}
