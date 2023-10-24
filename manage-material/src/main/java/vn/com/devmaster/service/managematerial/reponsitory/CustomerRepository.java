package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c.* from customer c where USERNAME =?1",nativeQuery = true)
    Customer findCustomerByName(String username);
}