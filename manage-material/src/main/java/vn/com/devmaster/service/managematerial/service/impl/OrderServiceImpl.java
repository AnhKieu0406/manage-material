package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.repository.CustomerRepository;
import vn.com.devmaster.service.managematerial.repository.OrderRepository;
import vn.com.devmaster.service.managematerial.repository.OrdersDetailRepository;
import vn.com.devmaster.service.managematerial.repository.OrdersPaymentRepository;
import vn.com.devmaster.service.managematerial.service.OrderService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrdersDetailRepository detailRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ShoppingCartImpl shoppingCart1;

    @Autowired
    OrdersPaymentRepository ordersPaymentsRepo;

    @Autowired
    HttpSession session;

    @Override
    @Transactional
    public Order save(Collection<CartItem> shoppingCart) {
        Order order = new Order();
        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(logInfo.getUsername());
//        customer.setAddress(customer.getListAddresses().);
        String idOrder = UUID.randomUUID().toString().substring(0, 10);
        order.setIdorders(idOrder);
        order.setIdCustomer(customer);
        order.setOrdersDate(new Date().toInstant());
//        order.setAddress(customer.getAddress());
//        order.setPhone(customer.getPhone());
        order.setTotalMoney(shoppingCart1.getAmount());
        order.setNameReciver(customer.getName());
        order.setNotes("CÓ");

        for (CartItem item : shoppingCart) {
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setIdord(order);
            ordersDetail.setIdproduct(item.getProduct());
            ordersDetail.setQty(item.getQuantity());
            ordersDetail.setPrice(item.getPrice()*item.getQuantity());
            detailRepository.save(ordersDetail);
        }

        orderRepo.save(order);
        return order;
    }

    @Override
    public List<Order> findAll(String username) {
        Customer customer = customerRepository.findByUsername(username);
        List<Order> orders = customer.getOrders();
        return orders;
    }

    @Override
    public List<Order> findALlOrders() {
        return null;
    }

    @Override
    public Order acceptOrder(Long id) {
        return null;
    }

    @Override
    public void cancelOrder(Long id) {

    }
}
