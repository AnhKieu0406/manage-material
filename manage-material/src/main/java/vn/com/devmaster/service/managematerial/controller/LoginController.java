package vn.com.devmaster.service.managematerial.controller;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dto.CustomerDto;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.UserService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartImpl shoppingCart;

    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String viewLogin(Principal principal) {
        return "/layout/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "layout/register";
    }


    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "/layout/register";
            }
            String username = customerDto.getUsername();
            Customer customer = customerService.findByUsername(username);
            if (customer != null) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("error", "Email has been register!");
                return "/layout/register";
            }
            if (customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                customerDto.setPassword(customerDto.getPassword());
                customerService.save(customerDto);
                model.addAttribute("success", "Register successfully!");
            } else {
                model.addAttribute("error", "Password is incorrect");
                model.addAttribute("customerDto", customerDto);
                return "/layout/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
        }
        return "/layout/register";
    }

    @Transactional
    @PostMapping("/do-login")
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,Principal principal) {
        try {
            Customer customer = userService.findByName(username);
            if (!customer.getPassword().equals(password)) {
                model.addAttribute("message", "Sai tên dăng nha hoặc mật khẩu");

            } else {

                model.addAttribute("customer", customer);
                model.addAttribute("cartItem", shoppingCart.getAllCartItem());
                model.addAttribute("Total", shoppingCart.totalAmount());
                model.addAttribute("cartCount", shoppingCart.getCount());
                session.setAttribute("customerName",customer);
                session.getAttribute("saveCart");

                return "features/cart-item";
            }

        } catch (Exception e) {
            model.addAttribute("message", "username invalid!");
        }

        return "/layout/login";
    }

}
