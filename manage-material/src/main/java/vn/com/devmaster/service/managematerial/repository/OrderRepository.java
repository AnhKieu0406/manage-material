package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.projection.OrderAllInformation;

import java.util.List;
import java.util.Optional;

import static vn.com.devmaster.service.managematerial.untils.Sql.ORDER_ALL_INFOMATION;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(nativeQuery = true)
//    List<Order> findAllOrder();

    Optional<Order> findById(Integer id);

    @Query(value =ORDER_ALL_INFOMATION,nativeQuery = true)
    List<OrderAllInformation> findAllById(Integer id);

}