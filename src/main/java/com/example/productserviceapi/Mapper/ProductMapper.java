package com.example.productserviceapi.Mapper;

import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Dto.UserProductSaveRequest;
import com.example.productserviceapi.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product convertToProduct(ProductSaveRequest productSaveRequest);

    ProductDTO convertToProductDTO(Product product);

    List<ProductDTO> convertToProductDTOs(List<Product> products);

    Product convertToUserProduct(UserProductSaveRequest userProductSaveRequest);
}
