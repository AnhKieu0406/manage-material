package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.dto.OrdersDetailDto;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.mapper.OrderMapper;
import vn.com.devmaster.service.managematerial.mapper.OrderdetailMapper;
import vn.com.devmaster.service.managematerial.reponsitory.OrderRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface OrderService {
    Order save(List<CartItem> shoppingCart, HttpSession session);

    List<Order> findAll(String username);

    List<Order> findALlOrders();

    Order acceptOrder(Long id);

    void cancelOrder(Long id);




}
