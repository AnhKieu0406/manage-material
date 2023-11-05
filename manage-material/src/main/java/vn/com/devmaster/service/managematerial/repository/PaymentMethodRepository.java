package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    @Query(value = "select p.* from  payment_method p where p.isactive= 1",nativeQuery = true)
    List<PaymentMethod> findAllByIsActive();


    @Query(value = "select p from PaymentMethod p where p.id = :idPayment")
    PaymentMethod findAllById(@Param("idPayment") Integer idPayment);

}