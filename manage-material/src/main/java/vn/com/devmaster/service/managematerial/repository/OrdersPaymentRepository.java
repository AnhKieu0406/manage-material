package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.OrdersPayment;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersPaymentRepository extends JpaRepository<OrdersPayment, Integer> {

    Optional<OrdersPayment> findAllById(Integer id);

    List<OrdersPayment> findListById(Integer id);
}