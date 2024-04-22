package za.ac.tut.kotashop.service;

import org.springframework.web.multipart.MultipartFile;
import za.ac.tut.kotashop.dto.CategoryDto;
import za.ac.tut.kotashop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto , MultipartFile file);
    void deleteProduct(Long productId);
    void editProduct(ProductDto productDto);
    List<ProductDto> findAllCategories();
}
