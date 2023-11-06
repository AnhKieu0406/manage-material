package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.PaymentMethod;
import vn.com.devmaster.service.managematerial.dommain.TransportMethod;

import java.util.List;

@Repository
public interface TransportMethodRepository extends JpaRepository<TransportMethod, Integer> {

    @Query(value = "select t from TransportMethod  t where t.isactive =1")
    List<TransportMethod> findAllByIsActive();

    @Query(value = "select  t from  TransportMethod t where  t.id = :idTransport ")
    TransportMethod findAllById(@Param("idTransport")Integer idTransport);
}