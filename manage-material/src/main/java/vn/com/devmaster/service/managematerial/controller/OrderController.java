package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.devmaster.service.managematerial.dommain.CartItem;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.OrdersDetail;
import vn.com.devmaster.service.managematerial.repository.*;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.OrderService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

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
    PaymentMethodRepository paymentMethodRepository;


    @GetMapping("/check-out/{username}")
    public String checkout(Model model, HttpSession session, @PathVariable(name = "username") String username) {
        Order order = new Order();
//        Customer customer1 = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(username);
//        Set<CartItem> cart = customer.getCartItems();

        String idOrder = UUID.randomUUID().toString().substring(0, 10);
        order.setIdorders(idOrder);
        order.setIdCustomer(customer);
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(order.getIdorders());
        order.setAddress(customer.getAddress());
        order.setPhone(customer.getPhone());
        order.setTotalMoney(shoppingCart.getAmount());
        order.setNameReciver(customer.getName());
        order.setNotes("CÓ");
        List<OrdersDetail> ordersDetailList = new ArrayList<>();
        Collection<CartItem> cart = (Collection<CartItem>) session.getAttribute("cart");
        for (CartItem item : cart) {
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setIdord(order);
            ordersDetail.setIdproduct(item.getProduct());
            ordersDetail.setQty(item.getQuantity());
            ordersDetail.setPrice(item.getPrice()*item.getQuantity());
            detailRepository.save(ordersDetail);
            ordersDetailList.add(ordersDetail);
        }
        order.setOrdersDetails(ordersDetailList);
        orderRepository.save(order);
        model.addAttribute("customer", customer);
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        model.addAttribute("bills", order);

        model.addAttribute("payment",paymentMethodRepository.findAllByIsActive());
        return "/features/checkout";
    }

}
