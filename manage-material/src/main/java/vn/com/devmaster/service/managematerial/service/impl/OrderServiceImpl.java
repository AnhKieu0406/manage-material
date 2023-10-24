package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.ShoppingCart;
import vn.com.devmaster.service.managematerial.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl  implements OrderService {
    @Override
    public Order save(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public List<Order> findAll(String username) {
        return null;
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
