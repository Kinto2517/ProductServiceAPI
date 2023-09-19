package com.example.productserviceapi.Controller.Contract;

import com.example.productserviceapi.Dto.*;
import com.example.productserviceapi.Entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductControllerContract {
    ProductDTO save(ProductSaveRequest productSaveRequest);

    ProductDTO findById(Long id);

    ProductDTO update(Long id, ProductSaveRequest productSaveRequest);

    Product updateDto(Long id, ProductDTO productDTO);

    void delete(Long id);

    List<ProductDTO> findAll();

    List<ProductDTO> searchProducts(String name, String code, String brand, BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductDTO> findUserProducts(Long id);

    ProductDTO saveUserProduct(UserProductSaveRequest userProductSaveRequest);

    void updatePage(Product product);
}
