package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.projection.OrderAllInformation;
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

    @Autowired
    TransportMethodRepository tranRepo;

    @Autowired
    OrdersTransportRepository ordTranRepo;


    @GetMapping("/check-out/{username}")
    public String checkout(Model model, HttpSession session
            , @PathVariable(name = "username") String username
//            , @RequestParam(required = false, name = "idPayment") Integer idPayment
//            , @RequestParam(required = false, name = "idTransport") Integer idTransport
    ) {

        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(username);
        if (logInfo == null) {
            return "redirect:/home";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());


        model.addAttribute("newPayment", new OrdersPayment());
//        model.addAttribute("idPayment", idPayment);
        model.addAttribute("listPayment", paymentMethodRepository.findAllByIsActive());
        model.addAttribute("listTransport",tranRepo.findAllByIsActive());


        return "/features/checkout";
    }


    @PostMapping("/saveOrder")
    public String addPayment( Model model, HttpSession session
            , @RequestParam(required = false, name = "idPayment") Integer idPayment
            , @RequestParam(required = false, name = "idTransport") Integer idTransport
    ) {
        Collection<CartItem> cart = (Collection<CartItem>) session.getAttribute("cart");
        PaymentMethod paymentMethod = paymentMethodRepository.findAllById(idPayment);

        TransportMethod transportMethod = tranRepo.findAllById(idTransport);
        Order order = orderService.save(cart);

        List<OrderAllInformation> list = orderRepository.findAllById(order.getIdCustomer().getId());

        model.addAttribute("order",list);

        OrdersPayment payment = OrdersPayment.builder()
                .idord(order)
                .idPayment(paymentMethod)
                .notes(paymentMethod.getNotes())
                .build();
        ordersPaymentRepository.save(payment);
        OrdersTransport ordersTransport = OrdersTransport.builder()
                .idord(order)
                .idTransport(transportMethod)
                .notes(1)
                .build();
        ordTranRepo.save(ordersTransport);
        return "/features/DonHang";
    }



    @GetMapping("/list_payment")
    public String ThanhToan(Model model) {
        List<PaymentMethod> list = paymentMethodRepository.findAllByIsActive();
        model.addAttribute("payment", list);
        return "features/payment/Payment";
    }

}
