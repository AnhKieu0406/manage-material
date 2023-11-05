package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.repository.*;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.OrderPaymentService;
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
    private OrderPaymentService orderPaySer;

    @Autowired
    OrdersDetailRepository detailRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private OrdersPaymentRepository ordersPaymentRepository;
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/check-out/{username}")
    public String checkout(Model model, HttpSession session
            , @PathVariable(name = "username") String username
            , @RequestParam(required = false, name = "idPayment") Integer idPayment
    ) {

        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(username);
        if (logInfo == null) {
            return "layout/index";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());


        model.addAttribute("newPayment", new OrdersPayment());
        model.addAttribute("idPayment", idPayment);
        model.addAttribute("listPayment", paymentMethodRepository.findAllByIsActive());

//        PaymentMethod paymentMethod = paymentMethodRepository.findAllById(idPayment);
//        session.setAttribute("paymentId",paymentMethod);
//        model.addAttribute("paymentId",paymentMethod);

        return "/features/checkout";
    }


    @PostMapping("/saveOrder")
    public String addPayment(@ModelAttribute("newPayment") OrdersPayment ordersPayment, Model model, HttpSession session
            , @RequestParam(required = false, name = "idPayment") Integer idPayment
    ) {
        Collection<CartItem> cart = (Collection<CartItem>) session.getAttribute("cart");
        PaymentMethod paymentMethod = paymentMethodRepository.findAllById(idPayment);
        Order order = orderService.save(cart);
        OrdersPayment payment = OrdersPayment.builder()
                .idord(order)
                .idPayment(idPayment)
                .notes(paymentMethod.getNotes())
                .build();

        ordersPaymentRepository.save(payment);
        return "redirect:/home";
    }

    @GetMapping("/list_payment")
    public String ThanhToan(Model model) {
        List<PaymentMethod> list = paymentMethodRepository.findAllByIsActive();
        model.addAttribute("payment", list);
        return "features/payment/Payment";
    }

}
