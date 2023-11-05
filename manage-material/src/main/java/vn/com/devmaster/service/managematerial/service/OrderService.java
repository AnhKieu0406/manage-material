package vn.com.devmaster.service.managematerial.service;

import vn.com.devmaster.service.managematerial.dommain.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;


public interface OrderService {
    Order save(Collection<CartItem> shoppingCart);

    List<Order> findAll(String username);

    List<Order> findALlOrders();

    Order acceptOrder(Long id);

    void cancelOrder(Long id);




}
