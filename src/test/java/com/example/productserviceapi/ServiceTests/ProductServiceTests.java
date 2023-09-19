package com.example.productserviceapi.ServiceTests;

import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Entity.Product;
import com.example.productserviceapi.Service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testSearchProducts() {
        ProductSaveRequest request = new ProductSaveRequest("test", "test",
                "test", "test", "test", BigDecimal.valueOf(5.3), 5, true);

        Product product = new Product("test", "test", "test",
                "test", "test", BigDecimal.valueOf(5.3), 5, true);

        when(productRepository.searchProducts(anyString(), anyString(), anyString(),any(),any()))
                .thenReturn(List.of(product));


    }
}
