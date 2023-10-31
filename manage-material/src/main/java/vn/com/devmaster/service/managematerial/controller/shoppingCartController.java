package vn.com.devmaster.service.managematerial.controller;

import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.*;


import vn.com.devmaster.service.managematerial.dto.CustomerDto;
import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.reponsitory.*;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.OrderService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;


@Controller
@RequestMapping("/shopping-cart")
public class shoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartImpl shoppingCart;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrdersDetailRepository detailRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/view-cart")
    public String viewCart(Model model, HttpSession session) {
        session.setAttribute("saveCart", shoppingCart.getAllCartItem());
        session.getAttribute("saveCart");
        model.addAttribute("cartItem", shoppingCart.getAllCartItem());
        model.addAttribute("Total", shoppingCart.totalAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/features/cart-item";
    }

    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Product product = productService.findById(id);
        if (product != null) {
            CartItem item = new CartItem();
            item.setId(item.getId());
            item.setProduct(product);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            shoppingCart.addCartItem(item);
//            cartItemRepository.save(item);
        }


        model.addAttribute("cartCount", shoppingCart.getCount());
        return "redirect:/shopping-cart/view-cart";

    }


    @GetMapping("/clear")
    public String clearCart() {
        shoppingCart.clear();
        return "redirect:/shopping-cart/view-cart";
    }

    @GetMapping("/del/{id}")
    public String removeCart(@PathVariable Integer id) {
        shoppingCart.remove(id);
        return "redirect:/shopping-cart/view-cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
        shoppingCart.update(id, qty);
        return "redirect:/shopping-cart/view-cart";
    }

    @GetMapping("/check-out/{username}")
    public String checkout(Model model, HttpSession session, @PathVariable(name = "username") String username) {
        Order bills = new Order();
        Customer customer = customerService.findByUsername(username);
        List<CartItem> cart = customer.getCartItems();
        model.addAttribute("customer", customer);
        model.addAttribute("cartItem", shoppingCart.getAllCartItem());
        model.addAttribute("Total", shoppingCart.totalAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());

        orderService.save(cart, session);
        model.addAttribute("bills", bills);
        return "/features/checkout";
    }


}
