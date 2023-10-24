package vn.com.devmaster.service.managematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.CustomUserDetail;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.service.UserService;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    UserService userService;
    @GetMapping("/login")
    public  String viewlogin( @ModelAttribute("customer") Customer customer){
        return "/layout/login";
    }
    @GetMapping("/login-check")
    public  String loginCheck(){

        CustomUserDetail userDetail = new CustomUserDetail(" admin", "123 " , "USER");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null,
                userDetail.getAuthorities());


        //Gắn user vao sercuriry context holder

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:/features/category/view_category";
    }


    @PostMapping("login")
    public String login(@ModelAttribute("customer")Customer customer1, Model model,@RequestParam("username")String username,@RequestParam("password")String password){
            try {
                Customer customer = userService.findByName(username);
                if(!customer.getPassword().equals(password)){
                    model.addAttribute("ssss","Sai tên dăng nha hoặc mật khẩu");

                }else {
                return "redirect:/shopping-cart/check-out";
                }

            }catch (Exception e){
                model.addAttribute("ssss","username invalid!");
            }

            return "redirect:/account/login";
    }

}
