package vn.com.devmaster.service.managematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dto.CustomerDto;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String viewLogin() {
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


    @PostMapping("/do-login")
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Customer customer = userService.findByName(username);
            if (!customer.getPassword().equals(password)) {
                model.addAttribute("message", "Sai tên dăng nha hoặc mật khẩu");

            } else {
                return "redirect:/shopping-cart/check-out";
            }

        } catch (Exception e) {
            model.addAttribute("message", "username invalid!");
        }

        return "redirect:/shopping-cart/check-out";
    }

}
