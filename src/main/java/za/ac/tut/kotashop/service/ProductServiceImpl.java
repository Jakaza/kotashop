package za.ac.tut.kotashop.service;

import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.repository.ProductRepository;

import java.util.Base64;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    // This will add product to database and product has image that must be stored as blob
    public void saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());
        byte[] imageBytes = Base64.getDecoder().decode(productDto.getProductImage());
        product.setProductImage(imageBytes);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public void editProduct(ProductDto productDto) {

    }

    @Override
    public List<ProductDto> findAllCategories() {
        return List.of();
    }
}
