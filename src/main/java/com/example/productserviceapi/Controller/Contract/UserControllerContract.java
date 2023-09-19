package com.example.productserviceapi.Controller.Contract;

import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Dto.UserSaveRequest;
import com.example.productserviceapi.Entity.User;

import java.util.List;

public interface UserControllerContract {

    UserDTO save(UserSaveRequest userSaveRequest);

    UserDTO findById(Long id);

    UserDTO update(Long id, UserSaveRequest userSaveRequest);

    void delete(Long id);

    List<UserDTO> findAll();



}

