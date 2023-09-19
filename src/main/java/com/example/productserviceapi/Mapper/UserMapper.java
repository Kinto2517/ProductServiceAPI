package com.example.productserviceapi.Mapper;

import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.ProductSaveRequest;
import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Dto.UserSaveRequest;
import com.example.productserviceapi.Entity.Product;
import com.example.productserviceapi.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUser(UserSaveRequest userSaveRequest);

    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDTOs(List<User> users);

}
