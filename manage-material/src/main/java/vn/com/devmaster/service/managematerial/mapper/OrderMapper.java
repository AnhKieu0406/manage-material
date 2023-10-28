package vn.com.devmaster.service.managematerial.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements EntityMapper<Order, OrderDto>{

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderdetailMapper orderdetailMapper;
    @Override
    public Order toEntity(OrderDto dto) {
        Order order = Order.builder()
                .id(dto.getId())
                .idorders(dto.getIdorders())
                .customer(customerMapper.toEntity(dto.getCustomer()))
                .ordersDate(dto.getOrdersDate())
                .totalMoney(dto.getTotalMoney())
                .notes(dto.getNotes())
                .nameReciver(dto.getNameReciver())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .ordersDetails(orderdetailMapper.toEntity(dto.getOrdersDetailDto()))
//               .ordersDetailDto(orderdetailMapper.toEntity(dto.getOrdersDetail()))
                .build();
        return order;
    }

    @Override
    public List<Order> toEntity(List<OrderDto> d) {
        List<Order> orders = new ArrayList<>();
        d.forEach(dto -> {
            orders.add(toEntity(dto));
        });
        return orders;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .idorders(order.getIdorders())
                .customer(customerMapper.toDto(order.getCustomer()))
                .ordersDetailDto(orderdetailMapper.toDto(order.getOrdersDetails()))
                .ordersDate(order.getOrdersDate())
                .totalMoney(order.getTotalMoney())
                .notes(order.getNotes())
                .nameReciver(order.getNameReciver())
                .address(order.getAddress())
                .phone(order.getPhone())
                .build();
        return orderDto;
    }

    @Override
    public List<OrderDto> toDto(List<Order> e) {
        List<OrderDto> list = new ArrayList<>();
        e.forEach(order -> {
            list.add(toDto(order));
        });
        return list;
    }
}
