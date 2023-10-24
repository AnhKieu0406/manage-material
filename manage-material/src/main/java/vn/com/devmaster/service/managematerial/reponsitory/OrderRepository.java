package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.Product;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(nativeQuery = true)
//    List<Order> findAllOrder();

    Order findAllById(Integer id);

}