package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.*;


import vn.com.devmaster.service.managematerial.repository.*;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.OrderService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;


import javax.servlet.http.HttpSession;
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
        session.setAttribute("saveCart", shoppingCart.getAllItem());
        session.getAttribute("saveCart");
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/features/cart-item";
    }

    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable("id") Integer id, Model model, HttpSession session) {
        Product product = productService.findById(id);
        int quantity = 1;
        if (product != null) {
            CartItem item = new CartItem();
            item.setId(product.getId());
            item.setProduct(product);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(item.getQuantity() + quantity);
            shoppingCart.add(item);
//    cartItemRepository.save(item);

        }
        session.setAttribute("cart",shoppingCart.getAllItem());

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




}
