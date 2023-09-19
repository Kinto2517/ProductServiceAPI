package com.example.productserviceapi.Controller;

import com.example.productserviceapi.Controller.Contract.UserControllerContract;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Dto.UserSaveRequest;
import com.example.productserviceapi.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContract userControllerContract;

    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers () {
        List<UserDTO> userDTOList = userControllerContract.findAll();

        if (userDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(userDTOList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("id") Long id) {
        UserDTO userDTO = userControllerContract.findById(id);

        if (userDTO == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(userDTO);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<UserDTO>> deleteUser (@RequestParam("id") Long id) {
        userControllerContract.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser (@PathVariable("id") Long id , @RequestBody UserSaveRequest userSaveRequest) {
        UserDTO userDTO = userControllerContract.update(id,userSaveRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser (@RequestBody UserSaveRequest userSaveRequest) {
        UserDTO userDTO = userControllerContract.save(userSaveRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/saveWeb")
    public RedirectView saveUserWeb (@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                     @RequestParam("username") String username, @RequestParam("password") String password) {
        UserSaveRequest userSaveRequest = new UserSaveRequest(username, password, firstName, lastName);
        userControllerContract.save(userSaveRequest);
        return new RedirectView("/api/v1/pages/login");
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(username);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return new RedirectView("/api/v1/pages/unauthorized");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        redirectAttributes.addFlashAttribute("user", user);

        return new RedirectView("/api/v1/pages/dashboard");
    }


}
