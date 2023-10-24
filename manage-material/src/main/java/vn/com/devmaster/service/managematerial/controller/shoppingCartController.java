package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.CartItem;


import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dommain.Product;

import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.reponsitory.CustomerRepository;
import vn.com.devmaster.service.managematerial.service.OrderService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@RequestMapping("/shopping-cart")
public class shoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartImpl shoppingCart;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderService orderService;
    @GetMapping("/view-cart")
    public String viewCart(Model model){
        model.addAttribute("cartItem",shoppingCart.getAllCartItem());
        model.addAttribute("Total",shoppingCart.totalAmount());
        model.addAttribute("cartCount",shoppingCart.getCount());
        return "/features/cart-item";
    }

    @GetMapping("/add-cart/{id}")
    public String addCart(HttpServletRequest request, @PathVariable Integer id, Model model){
        Product product = productService.findById(id);
        if (product!=null){
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            shoppingCart.addCartItem(item);
        }
        model.addAttribute("cartCount",shoppingCart.getCount());
        return "redirect:/shopping-cart/view-cart";

    }


    @GetMapping("/clear")
    public String clearCart( ){
        shoppingCart.clear();
        return  "redirect:/shopping-cart/view-cart";
    }
    @GetMapping("/del/{id}")
    public String removeCart(@PathVariable Integer id ){
        shoppingCart.remove(id);
        return  "redirect:/shopping-cart/view-cart";
    }
    @PostMapping("/update")
    public String update(@RequestParam("id")Integer id, @RequestParam Integer qty){
        shoppingCart.update(id,qty);
        return  "redirect:/shopping-cart/view-cart";
    }

    @GetMapping("/check-out")
    public String checkout(Model model){
        model.addAttribute("cartItem",shoppingCart.getAllCartItem());
        model.addAttribute("Total",shoppingCart.totalAmount());
        model.addAttribute("cartCount",shoppingCart.getCount());
        return "/features/checkout";
    }
//    @PostMapping("save")
}
