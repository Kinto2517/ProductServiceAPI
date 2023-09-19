package com.example.productserviceapi.ControllerTests;

import com.example.productserviceapi.Controller.Contract.UserControllerContract;
import com.example.productserviceapi.Controller.UserController;
import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Dto.UserSaveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Mock
    private UserControllerContract userControllerContract;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldGetAllProducts() {
        List<UserDTO> userDTOList = new ArrayList<>();

        UserDTO userDTO = new UserDTO(1L, "test","test", "test", "100");

        userDTOList.add(userDTO);

        when(userControllerContract.findAll()).thenReturn(userDTOList);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(userDTOList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldGetProductById() {
        UserDTO userDTO = new UserDTO(1L, "test","test", "test", "100");

        when(userControllerContract.findById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(userDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldSaveUser() {
        UserSaveRequest userSaveRequest = new UserSaveRequest( "test","test", "test", "100");

        UserDTO userDTO = new UserDTO(1L, "test","test", "test", "100");

        when(userControllerContract.save(userSaveRequest)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.saveUser(userSaveRequest);

        assertEquals(userDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }


    @Test
    public void shouldDeleteUser() {
        UserDTO userDTO = new UserDTO(1L, "test","test", "test", "100");

        ResponseEntity<List<UserDTO>> response = userController.deleteUser(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

}
