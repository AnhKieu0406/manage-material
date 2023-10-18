package vn.com.devmaster.service.managematerial.mapper;

import org.springframework.stereotype.Component;
import vn.com.devmaster.service.managematerial.dommain.OrdersDetail;
import vn.com.devmaster.service.managematerial.dto.OrdersDetailDto;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderdetailMapper implements EntityMapper<OrdersDetail, OrdersDetailDto>{

    @Override
    public OrdersDetail toEntity(OrdersDetailDto ordersDetailDto) {
        OrdersDetail ordersDetail =OrdersDetail.builder()
                .id(ordersDetailDto.getId())
                .price(ordersDetailDto.getPrice())
                .qty(ordersDetailDto.getQty())
                .build();
        return ordersDetail;
    }

    @Override
    public List<OrdersDetail> toEntity(List<OrdersDetailDto> d) {
        List<OrdersDetail> list = new ArrayList<>();
        d.forEach(ordersDetailDto -> {
            list.add(toEntity(ordersDetailDto));
        });
        return list;
    }

    @Override
    public OrdersDetailDto toDto(OrdersDetail ordersDetail) {
        return null;
    }

    @Override
    public List<OrdersDetailDto> toDto(List<OrdersDetail> e) {
        return null;
    }



}
