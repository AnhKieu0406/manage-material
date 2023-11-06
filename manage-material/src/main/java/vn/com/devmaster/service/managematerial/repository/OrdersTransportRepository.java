package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.OrdersTransport;
@Repository
public interface OrdersTransportRepository extends JpaRepository<OrdersTransport, Integer> {
}