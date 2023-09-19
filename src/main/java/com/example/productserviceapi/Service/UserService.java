package com.example.productserviceapi.Service;

import com.example.productserviceapi.Base.BaseEntityService;
import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.UserSaveRequest;
import com.example.productserviceapi.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {

    private final UserRepository repository;
    private final ProductRepository productRepository;

    private static final Logger logger = LogManager.getLogger(UserService.class);


    public UserService(UserRepository repository, UserRepository repository1, ProductRepository productRepository) {
        super(repository);
        this.repository = repository1;
        this.productRepository = productRepository;
    }

    public User update(Long id, UserSaveRequest userSaveRequest) {

        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userSaveRequest.username());
        user.setPassword(userSaveRequest.password());
        user.setFirstName(userSaveRequest.firstName());
        user.setLastName(userSaveRequest.lastName());

        logger.info("User updated: " + user.toString());

        return saveCrypt(user);
    }

    public User saveCrypt(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = new User(user.getUsername(), hashedPassword, user.getFirstName(), user.getLastName());
        return repository.save(newUser);
    }
}

