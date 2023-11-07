package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.repository.ProductRepository;
import vn.com.devmaster.service.managematerial.service.CategoryService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    ProductService productSer;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categorySer;


    @Autowired
    ShoppingCartImpl shoppingCart;


    @GetMapping("")
    public  String showIndex(HttpSession session){
        Customer customer = (Customer) session.getAttribute("customerName");
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
    public String home(Model model, HttpSession session){
        model.addAttribute("products", productSer.findAllProductByCategory());
        Customer customer = (Customer) session.getAttribute("customerName");
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/layout/home";
    }

    @GetMapping("/store")
    public String store(Model model){
//        model.addAttribute("products", productSer.findAllProductByCategory());
        model.addAttribute("products", productSer.findAllProduct());
        model.addAttribute("categories",categorySer.getAllCategory());
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/features/store";
    }

    @GetMapping("/store/category/{id}")
    public String findProductById(@PathVariable("id") Integer id, Model model) {
        List<Category> category = categorySer.getAllCategory();
        List<ProductByClassId> list = productRepository.findAllByCategory_Id(id);
        model.addAttribute("categories",category);
        model.addAttribute("products", list);

        return "/features/store";
    }


}
