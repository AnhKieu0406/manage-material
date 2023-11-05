package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
      @Query(value = "select c from Customer c where c.username = :username")
        Customer findByUsername(@Param("username") String username);
}