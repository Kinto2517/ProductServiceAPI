package com.example.productserviceapi.Dao;

import com.example.productserviceapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p " +
            "WHERE (:name is null OR p.name LIKE %:name%) " +
            "AND (:code is null OR p.code = :code) " +
            "AND (:brand is null OR p.brand = :brand) " +
            "AND (:minPrice is null OR p.price >= :minPrice) " +
            "AND (:maxPrice is null OR p.price <= :maxPrice) " +
            "AND p.deleted = false")
    List<Product> searchProducts(@Param("name") String name,@Param("code") String code,
                                 @Param("brand") String brand,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice")
                                 BigDecimal maxPrice);

    @Query("SELECT p FROM Product p WHERE p.user.id = :id AND p.deleted = false")
    List<Product> findUserProducts(Long id);
}