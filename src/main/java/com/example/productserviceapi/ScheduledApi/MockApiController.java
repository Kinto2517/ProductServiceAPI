package com.example.productserviceapi.ScheduledApi;

import com.example.productserviceapi.Controller.Contract.ProductControllerContract;
import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mock")
public class MockApiController {

    @Autowired
    private final ProductControllerContract productControllerContract;

    @Autowired
    private final ProductRepository productRepository;

    public MockApiController(ProductControllerContract productControllerContract, ProductRepository productRepository) {
        this.productControllerContract = productControllerContract;
        this.productRepository = productRepository;
    }

    @GetMapping("/updateStock")
    public String updateStockStatus() {
        int newStockStatus = generateRandomStockStatus();

        List<ProductDTO> products = productControllerContract.findAll();

        for (ProductDTO product : products) {
            Optional<Product> updatedProduct = productRepository.findById(product.id());

            updatedProduct.get().setStockStatus(newStockStatus);

            productRepository.save(updatedProduct.get());
        }


        return "Stock status updated successfully to " + newStockStatus;
    }

    private int generateRandomStockStatus() {
        return (int) (Math.random() * 100);
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStockStatusDaily() {
        String result = updateStockStatus();
        System.out.println("Daily Stock Update: " + result);
    }
}

