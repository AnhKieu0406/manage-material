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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrdersDetailRepository detailRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public Order save(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setCustomer(shoppingCart.getCustomer());
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(order.getIdorders());
        order.setTotalMoney(shoppingCart.getTotalPrice());
        List<OrdersDetail> ordersDetailList = new ArrayList<>();
        for (CartItem item : shoppingCart.getCartItems()) {
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setOrder(order);
            ordersDetail.setProduct(item.getProduct());
            detailRepository.save(ordersDetail);
            ordersDetailList.add(ordersDetail);
        }
        order.setOrdersDetails(ordersDetailList);

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
