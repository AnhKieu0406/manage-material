package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dommain.CustomUserDetail;
import vn.com.devmaster.service.managematerial.service.CategoryService;
import vn.com.devmaster.service.managematerial.service.ProductService;

@Controller
public class CommonController {

    @Autowired
    ProductService productSer;


    @GetMapping("")
    public  String showIndex(){
        return "layout/index";
    }

//    @GetMapping("/login-check")
//    public  String loginCheck(){
//
//        CustomUserDetail userDetail = new CustomUserDetail(" admin", "123 " , "USER");
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null,
//                userDetail.getAuthorities());
//
//
//        //Gắn user vao sercuriry context holder
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        return "redirect:/features/category/view_category";
//    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("products", productSer.findAllProductByCategory());
        return "/layout/home";
    }


}
