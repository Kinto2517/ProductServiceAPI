package com.example.productserviceapi.Entity;

import com.example.productserviceapi.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockStatus;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product(String name, String code, String description, String brand, String currency, BigDecimal price, int stockStatus, boolean deleted) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.currency = currency;
        this.price = price;
        this.stockStatus = stockStatus;
        this.deleted = deleted;
    }

    public Product(String name, String code, String description, String brand, String currency, BigDecimal price, int stockStatus, boolean deleted, User user) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.currency = currency;
        this.price = price;
        this.stockStatus = stockStatus;
        this.deleted = deleted;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                ", stockStatus=" + stockStatus +
                ", deleted=" + deleted +
                '}';
    }
}