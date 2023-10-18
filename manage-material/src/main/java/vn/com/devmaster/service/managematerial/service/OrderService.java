package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.OrdersPayment;
import vn.com.devmaster.service.managematerial.dommain.PaymentMethod;
import vn.com.devmaster.service.managematerial.dto.OrderDto;
import vn.com.devmaster.service.managematerial.dto.OrdersDetailDto;
import vn.com.devmaster.service.managematerial.mapper.OrderMapper;
import vn.com.devmaster.service.managematerial.mapper.OrderdetailMapper;
import vn.com.devmaster.service.managematerial.reponsitory.OrderRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScope
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderMapper orderMap;

    @Autowired
    private OrderdetailMapper orderdetailMapper;

    Map<Integer,OrderDto>  maps =new HashMap<>();

    public void addOrder(OrderDto item){
        OrderDto orderDto1 = maps.get(item.getId());
        if (orderDto1 == null){
            maps.put(item.getId(),item);
        }else {
            orderDto1.setTotalMoney(item.getOrdersDetail().getPrice()*item.getOrdersDetail().getQty());
        }
    }

    public void remove(int id){
        maps.remove(id);
    }






    public List<OrderDto> findAll(){
        List<Order> list = orderRepo.findAll();
        return orderMap.toDto(list);
    }

    public void save(OrderDto orderDto){
        Order order = orderMap.toEntity(orderDto);
//        PaymentMethod paymentMethod = new PaymentMethod();// tim theo id
//        OrdersPayment ordersPayment = OrdersPayment
//                .builder()
//                .ordersPayment(ordersPayment)
//                .idord(order)
//                .total(1000)
//                .status("true")
//                .notes("hgdsjfghdshjhfghj")
//                .build();
        orderRepo.save(order);
    }
    @Transactional
    public void save(List<OrderDto> dtoList){
        List<Order> orders = orderMap.toEntity(dtoList);
        orderRepo.saveAll(orders);
    }

    public void deleteById(Integer id){
        orderRepo.deleteById(id);
    }
}
