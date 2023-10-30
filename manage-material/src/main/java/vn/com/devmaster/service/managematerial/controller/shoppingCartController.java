package vn.com.devmaster.service.managematerial.controller;

import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.*;


import vn.com.devmaster.service.managematerial.dto.CustomerDto;
import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.reponsitory.CartItemRepository;
import vn.com.devmaster.service.managematerial.reponsitory.CustomerRepository;
import vn.com.devmaster.service.managematerial.reponsitory.OrderRepository;
import vn.com.devmaster.service.managematerial.reponsitory.OrdersDetailRepository;
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

    @GetMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItem", shoppingCart.getAllCartItem());
        model.addAttribute("Total", shoppingCart.totalAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/features/cart-item";
    }

    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable Integer id, Model model,HttpSession session) {
        Product product = productService.findById(id);
        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(logInfo.getUsername());

        List<CartItem> list = customer.getCartItems();
        if (product != null) {
            CartItem item = new CartItem();
            item.setId(item.getId());
            item.setProduct(product);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            shoppingCart.addCartItem(item);
            list.add(item);
            session.setAttribute("myCart",item);
        }

            session.setAttribute("cart",list);
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
    public String update(@RequestParam("id") Integer id, @RequestParam Integer qty) {
        shoppingCart.update(id, qty);
        return "redirect:/shopping-cart/view-cart";
    }

    @GetMapping("/check-out")
    public String checkout(Model model, HttpSession session,HttpServletRequest request) {
        Order bills = new Order();
        Customer logInfor = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(((Customer) session.getAttribute("customerName")).getUsername());
//        CartItem cart = (CartItem) session.getAttribute("myCart");

        List<CartItem> cart = customerService.findByUsername(logInfor.getUsername()).getCartItems();
        List<Order> orders = new ArrayList<>();
        model.addAttribute("customer", customer);
        model.addAttribute("cartItem", shoppingCart.getAllCartItem());
        model.addAttribute("Total", shoppingCart.totalAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());

        orderService.save(cart,session);
        model.addAttribute("bills", bills);
        return "/features/checkout";
    }


}
