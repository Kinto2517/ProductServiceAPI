package com.example.productserviceapi.Controller;

import com.example.productserviceapi.Controller.Contract.ProductControllerContract;
import com.example.productserviceapi.Controller.Contract.UserControllerContract;

import com.example.productserviceapi.Dao.ProductRepository;
import com.example.productserviceapi.Dao.UserRepository;
import com.example.productserviceapi.Dto.ProductDTO;
import com.example.productserviceapi.Dto.UserDTO;
import com.example.productserviceapi.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/pages")
public class ApiController {

    private final UserControllerContract userControllerContract;
    private final ProductControllerContract productControllerContract;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public ApiController(UserControllerContract userControllerContract, ProductControllerContract productControllerContract) {
        this.userControllerContract = userControllerContract;
        this.productControllerContract = productControllerContract;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/unauthorized")
    public String unauthorizedPage() {
        return "unauthorized";
    }
    @GetMapping("/dashboard")
    public String dashboardPage(HttpServletRequest request, Model model, @ModelAttribute("user") User user) {

        if (!isLoggedIn(request)) {
            return "redirect:/api/v1/pages/login";
        }

        User user1 = getCurrentUser(request);
        List<ProductDTO> userProducts = productControllerContract.findUserProducts(user.getId());

        model.addAttribute("userProducts", userProducts);
        model.addAttribute("user", user);

        return "dashboard";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the user's session
        }
        return "redirect:/api/v1/pages/login"; // Redirect to the login page or any other desired page
    }
    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return (User) session.getAttribute("user");
        }

        System.out.println("Session is null");
        return null;
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            return true;
        }

        return false;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


}
