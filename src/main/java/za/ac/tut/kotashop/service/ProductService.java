package za.ac.tut.kotashop.service;

import org.springframework.web.multipart.MultipartFile;
import za.ac.tut.kotashop.dto.CategoryDto;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto , MultipartFile file) throws IOException;
    void deleteProduct(Long productId);
    void editProduct(ProductDto productDto);
    Product findById(Long id);
    List<ProductDto> findAllProducts();

    List<Product> getAllProductsWithoutImage();
}
