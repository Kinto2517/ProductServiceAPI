package com.example.productserviceapi.Controller;

import com.example.productserviceapi.Controller.Contract.ProductControllerContract;
import com.example.productserviceapi.Controller.Contract.UserControllerContract;
import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Dto.UserProductSaveRequest;
import com.example.productserviceapi.Entity.Product;
import com.example.productserviceapi.Entity.User;
import com.example.productserviceapi.Mapper.ProductMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductControllerContract productControllerContract;

    @Autowired
    private final ProductRepository productRepository;

    @PostMapping("/save")
    public ResponseEntity<ProductDTO> saveProduct (@RequestBody ProductSaveRequest productSaveRequest) {
        ProductDTO productDTO = productControllerContract.save(productSaveRequest);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/userSave")
    public ResponseEntity<ProductDTO> saveUserProduct (@RequestBody UserProductSaveRequest userProductSaveRequest) {
        ProductDTO productDTO = productControllerContract.saveUserProduct(userProductSaveRequest);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct (@PathVariable("id") Long id , @RequestBody ProductSaveRequest productSaveRequest) {
        ProductDTO productDTO = productControllerContract.update(id,productSaveRequest);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/updateproduct")
    public String updateProductPage (@RequestParam("id") Long id,  Model model) {
        ProductDTO product = productControllerContract.findById(id);
        model.addAttribute("product", product);

        return "updateproduct";
    }


    @PostMapping("/updateProduct")
    public RedirectView updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return new RedirectView("/api/v1/pages/dashboard");
        }

        productControllerContract.updatePage(product);

        return new RedirectView("/api/v1/pages/dashboard");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable("id") Long id) {
        productControllerContract.delete(id);
        return ResponseEntity.ok().build();
    }

        @GetMapping("/delete")
        public RedirectView deleteProduct (@RequestParam("id") Long id) {
            productControllerContract.delete(id);
            return new RedirectView("/api/v1/pages/dashboard");
        }


    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts () {
        List<ProductDTO> productDTOList = productControllerContract.findAll();

        if (productDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(productDTOList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById (@RequestParam("id") Long id) {
        ProductDTO productDTO = productControllerContract.findById(id);

        if (productDTO == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(productDTO);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<ProductDTO> products = productControllerContract.searchProducts(name, code, brand, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}
