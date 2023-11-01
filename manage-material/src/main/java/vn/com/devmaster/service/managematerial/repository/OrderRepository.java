package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(nativeQuery = true)
//    List<Order> findAllOrder();

    Order findAllById(Integer id);

}