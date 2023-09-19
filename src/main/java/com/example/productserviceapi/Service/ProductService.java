package com.example.productserviceapi.Service;

import com.example.productserviceapi.Base.BaseEntityService;
import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Entity.Product;
import com.example.productserviceapi.Mapper.ProductMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService extends BaseEntityService<Product, ProductRepository> {

    private final ProductRepository repository;
    private final UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public ProductService(ProductRepository repository, ProductRepository repository1, UserRepository userRepository) {
        super(repository);
        this.repository = repository1;
        this.userRepository = userRepository;
    }

    public Product update(Long id, ProductSaveRequest productSaveRequest) {

        Product product = findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productSaveRequest.name());
        product.setPrice(productSaveRequest.price());
        product.setBrand(productSaveRequest.brand());
        product.setCode(productSaveRequest.code());
        product.setCurrency(productSaveRequest.currency());
        product.setDescription(productSaveRequest.description());
        product.setStockStatus(productSaveRequest.stockStatus());

        logger.info("Product updated: " + product.toString());

        return save(product);
    }

    public void safeDelete(Product product) {
        logger.info("Product safeDelete: " + product.toString());

        product.setDeleted(true);
        save(product);
    }


    public List<Product> searchProducts(String name, String code, String brand, BigDecimal minPrice, BigDecimal maxPrice) {
        logger.info("Product searchProducts: " + name + " " + code + " " + brand + " " + minPrice + " " + maxPrice);

        return repository.searchProducts(name, code, brand, minPrice, maxPrice);
    }

    public List<Product> findUserProducts(Long id) {
        logger.info("Product findUserProducts: " + id);

        return repository.findUserProducts(id);
    }

    public Product saveUserProduct(Product product) {
        logger.info("Product saveUserProduct: " + product.toString());

        return save(product);
    }

    public void updatePage(Product product) {
        logger.info("Product updatePage: " + product.toString());

        save(product);
    }

    public ProductDTO saveProduct(ProductSaveRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setBrand(request.brand());
        product.setCode(request.code());
        product.setCurrency(request.currency());
        product.setDescription(request.description());
        product.setStockStatus(request.stockStatus());
        product.setDeleted(false);

        logger.info("Product saved: " + product.toString());

        ProductMapper productMapper = ProductMapper.INSTANCE;
        ProductDTO productDTO = productMapper.convertToProductDTO(product);

        return productDTO;
    }

    public Product updateDto(Long id, ProductDTO productDTO) {
        Product product = findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setBrand(productDTO.brand());
        product.setCode(productDTO.code());
        product.setCurrency(productDTO.currency());
        product.setDescription(productDTO.description());
        product.setStockStatus(productDTO.stockStatus());

        logger.info("Product updated: " + product.toString());

        return save(product);
    }
}
