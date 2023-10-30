package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.dto.OrdersDetailDto;
import vn.com.devmaster.service.managematerial.reponsitory.CustomerRepository;
import vn.com.devmaster.service.managematerial.reponsitory.OrderRepository;
import vn.com.devmaster.service.managematerial.reponsitory.OrdersDetailRepository;
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

    @Override
    @Transactional
    public Order save(List<CartItem> shoppingCart, HttpSession session) {
        Order order = new Order();

        Customer logInfo = (Customer) session.getAttribute("customerName");
        Customer customer = customerService.findByUsername(logInfo.getUsername());
//        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
       cart = customer.getCartItems();
        String idOrder = logInfo.getUsername();
        idOrder = UUID.randomUUID().toString().substring(0, 10);
        order.setIdorders(idOrder);
        order.setCustomer(customer);
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(order.getIdorders());
        order.setAddress(customer.getAddress());
        order.setPhone(customer.getPhone());
        order.setTotalMoney(shoppingCart1.totalAmount());
        order.setNameReciver(customer.getName());
        order.setNotes("CÓ");
        List<OrdersDetail> ordersDetailList = new ArrayList<>();
        for (CartItem item : cart) {
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setOrder(order);
            ordersDetail.setProduct(item.getProduct());
            detailRepository.save(ordersDetail);
            ordersDetailList.add(ordersDetail);
        }
        order.setOrdersDetails(ordersDetailList);
        orderRepo.save(order);
        return order;
    }

    @Override
    public List<Order> findAll(String username) {
        Customer customer = customerRepository.findByUsername(username);
        List<Order> orders = customer.getOrderSet();
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
