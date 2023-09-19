package com.example.productserviceapi.Service;

import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String plainPassword) {
        User user = userRepository.findByUsername(username);

        return BCrypt.checkpw(plainPassword, user.getPassword());
    }
}