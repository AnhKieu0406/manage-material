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
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

//    @Autowired
//    ProductService productService;

    @Autowired
    ShoppingCartImpl shoppingCart;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerAddressRepository addressRepository;

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
    @Autowired
    private OrdersAddressRepository ordersAddressRepository;

    @Autowired
    OrdersDetailRepository detail1Repository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/check-out")
    public String checkout(Model model, HttpSession session) {

        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(logInfo.getUsername());
        List<CustomerAddress> customerAddress = addressRepository.findAllByIdCustomer(customer.getId());

        if (logInfo == null) {
            return "redirect:/home";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("listAddress",customerAddress);
        model.addAttribute("addressChose", customerAddress.get(0));


        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());


//        model.addAttribute("newPayment", new OrdersPayment());
        model.addAttribute("listPayment", paymentMethodRepository.findAllByIsActive());
        model.addAttribute("listTransport", tranRepo.findAllByIsActive());


        return "/features/checkout";
    }

    @GetMapping("/updateAddress/{id}")
    public  String updateAdd(){
        return  "/features/checkout";
    }


    @PostMapping("/saveOrder")
    public String addPayment(HttpServletRequest request
            , Model model
            , HttpSession session
            ,@RequestParam(required = false,name = "idAddress")Integer idAddress
            ,@RequestParam(required = false, name = "idPayment") Integer idPayment
            ,@RequestParam(required = false, name = "idTransport") Integer idTransport
    ) {
//        Collection<CartItem> cart = (Collection<CartItem>) session.getAttribute("cart");
//        Customer customer = customerService.findByUsername(username);

//        session.setAttribute("cusAddress",address);


        Collection<CartItem> cart1 = shoppingCart.getAllItem();

        Order order = orderService.save(cart1);


        //save address by id cutomeraddress

        CustomerAddress address = addressRepository.findAllById(idAddress);
        OrdersAddress ordersAddress = new OrdersAddress();
        ordersAddress.setIdaddress(address);
        ordersAddress.setIdorder(order);
        ordersAddressRepository.save(ordersAddress);

        order.setNameReciver(ordersAddress.getIdaddress().getName());
        order.setAddress(ordersAddress.getIdaddress().getAdress());
        order.setPhone(ordersAddress.getIdaddress().getPhone());
        order.setIsactive(1);
        orderRepository.save(order);
        List<OrderAllInformation> list = orderRepository.findAllById(order.getId());
        model.addAttribute("order", order);

        /*
        * save OrderPayment theo id order
        * */
        PaymentMethod paymentMethod = paymentMethodRepository.findAllById(idPayment);
        OrdersPayment payment = OrdersPayment.builder()
                .idord(order)
                .idPayment(paymentMethod)
                .notes(paymentMethod.getNotes())
                .build();
        ordersPaymentRepository.save(payment);

        TransportMethod transportMethod = tranRepo.findAllById(idTransport);
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

    @GetMapping("/list_order")
    public String ViewOrder(Model model){
        List<Order> list = orderRepository.findAll();
        model.addAttribute("orders",list);
        return "features/orders/listOrder";
    }

    @GetMapping("/detail_order/{idOrder}")
    public String detailDonHang(Model model,
                                 @PathVariable("idOrder")Integer idOrder){
        List<OrdersDetail> list = detail1Repository.findAllById(idOrder);
        Order orders = orderRepository.findOrderById(idOrder);
        model.addAttribute("order_detail",list);
        model.addAttribute("order",orders);

        return "features/orders/detail_order";
    }

    @GetMapping("/xacNhanThanhtoan/{idOrder}")
    public String hoanTat(@PathVariable("idOrder")Integer idOrder){
        Order orders = orderRepository.findOrderById(idOrder);
        orders.setIsactive(0);
        orderRepository.save(orders);
        return "redirect:/order/detail_order/{idOrder}";
    }

    @GetMapping("/add_address")
    public String saveAddress(@ModelAttribute(name = "customerAddress")CustomerAddress customerAddress
                              ,@RequestParam("phone")String phone
                              ,@RequestParam("address") String adress
                              ,@RequestParam("name")String name
            , Model model
            , HttpSession session){
        Customer customer = (Customer) session.getAttribute("customerName");
       customerAddress.setIdCustomer(customer);
       customerAddress.setName(name);
       customerAddress.setPhone(phone);
       customerAddress.setAdress(adress);
       addressRepository.save(customerAddress);
       return "redirect:/order/checkout";
    }

}
