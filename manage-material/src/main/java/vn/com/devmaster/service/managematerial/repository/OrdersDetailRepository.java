package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.OrdersDetail;

import java.util.List;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer> {
//    @Query("select o from Order o where o.idCustomer.id = ?1")
//    List<Order> findAllByCustomerId(Long id);
    @Query("select od from OrdersDetail od where od.idord.id = :idOrder")
    List<OrdersDetail> findAllById(@Param("idOrder")Integer idOrder);
}