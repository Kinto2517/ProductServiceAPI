package com.example.productserviceapi.Controller.Contract.Impl;

import com.example.productserviceapi.Controller.Contract.UserControllerContract;

import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Dto.UserSaveRequest;
import com.example.productserviceapi.Entity.User;
import com.example.productserviceapi.Mapper.UserMapper;
import com.example.productserviceapi.Service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;

    @Override
    public UserDTO save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        user = userService.saveCrypt(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO update(Long id, UserSaveRequest userSaveRequest) {
        User updatedUser = userService.update(id, userSaveRequest);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userService.delete(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }


}
