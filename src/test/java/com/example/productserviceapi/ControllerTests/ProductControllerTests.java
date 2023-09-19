package com.example.productserviceapi.ControllerTests;

import com.example.productserviceapi.Controller.Contract.ProductControllerContract;
import com.example.productserviceapi.Controller.ProductController;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTests {

    @Mock
    private ProductControllerContract productControllerContract;

    @InjectMocks
    private ProductController productController;

    @Test
    public void shouldGetAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO(1L, "test","test", "test", "100", "tet", BigDecimal.valueOf(5.2),5, true);

        productDTOList.add(productDTO);

        when(productControllerContract.findAll()).thenReturn(productDTOList);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertEquals(productDTOList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldGetProductById() {
        ProductDTO productDTO = new ProductDTO(1L, "test","test", "test", "100", "tet", BigDecimal.valueOf(5.2),5, true);

        when(productControllerContract.findById(1L)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        assertEquals(productDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldSaveProduct() {
        ProductSaveRequest productSaveRequest = new ProductSaveRequest("test","test", "test", "100", "tet", BigDecimal.valueOf(5.3),5, true);

        ProductDTO productDTO = new ProductDTO(1L, "test","test", "test", "100", "tet", BigDecimal.valueOf(5.2),5, true);

        when(productControllerContract.save(productSaveRequest)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.saveProduct(productSaveRequest);

        assertEquals(productDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }


    @Test
    public void shouldDeleteProduct() {
        ProductDTO productDTO = new ProductDTO(1L, "test","test", "test", "100", "tet", BigDecimal.valueOf(5.2),5, true);

        ResponseEntity<ProductDTO> response = productController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

}
