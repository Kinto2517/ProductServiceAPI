package com.example.productserviceapi.Controller.Contract.Impl;

import com.example.productserviceapi.Controller.Contract.ProductControllerContract;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Dto.UserProductSaveRequest;
import com.example.productserviceapi.Entity.User;
import com.example.productserviceapi.Service.ProductService;
import com.example.productserviceapi.Mapper.ProductMapper;
import com.example.productserviceapi.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductControllerContractImpl implements ProductControllerContract {

    private final ProductService productService;
    private final UserRepository userRepository;


    @Override
    public ProductDTO save(ProductSaveRequest productSaveRequest) {
        Product product = ProductMapper.INSTANCE.convertToProduct(productSaveRequest);
        product = productService.save(product);
        return ProductMapper.INSTANCE.convertToProductDTO(product);
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.INSTANCE.convertToProductDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductSaveRequest productSaveRequest) {
        Product updatedProduct = productService.update(id, productSaveRequest);
        return ProductMapper.INSTANCE.convertToProductDTO(updatedProduct);
    }

    @Override
    public Product updateDto(Long id, ProductDTO productDTO) {
        Product updatedProduct = productService.updateDto(id, productDTO);
        return updatedProduct;
    }

    @Override
    public void delete(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productService.safeDelete(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productService.findAll();
        return ProductMapper.INSTANCE.convertToProductDTOs(products);
    }

    @Override
    public List<ProductDTO> searchProducts(String name, String code, String brand, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productService.searchProducts(name, code, brand, minPrice, maxPrice);
        return ProductMapper.INSTANCE.convertToProductDTOs(products);
    }

    @Override
    public List<ProductDTO> findUserProducts(Long id) {
        List<Product> products = productService.findUserProducts(id);
        return ProductMapper.INSTANCE.convertToProductDTOs(products);
    }

    @Override
    public ProductDTO saveUserProduct(UserProductSaveRequest userProductSaveRequest) {
        Product product = ProductMapper.INSTANCE.convertToUserProduct(userProductSaveRequest);
        User user = userRepository.findById(userProductSaveRequest.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        product.setUser(user);
        product = productService.saveUserProduct(product);
        return ProductMapper.INSTANCE.convertToProductDTO(product);
    }

    @Override
    public void updatePage(Product product) {
        productService.updatePage(product);
    }
}
