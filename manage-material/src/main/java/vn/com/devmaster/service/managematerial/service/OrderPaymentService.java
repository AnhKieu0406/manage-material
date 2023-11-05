package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.OrdersPayment;
import vn.com.devmaster.service.managematerial.repository.OrdersPaymentRepository;

@Service
public class OrderPaymentService {
    @Autowired
    OrdersPaymentRepository repository;

    public OrdersPayment  save(OrdersPayment entity){
       return repository.save(entity);

    }
}
