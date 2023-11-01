package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
      @Query(value = "select c.* from customer c where USERNAME = ?",nativeQuery = true)
        Customer findByUsername(String username);
}