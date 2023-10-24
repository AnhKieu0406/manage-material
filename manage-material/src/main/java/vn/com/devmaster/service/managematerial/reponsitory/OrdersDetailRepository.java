package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.OrdersDetail;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer> {
}