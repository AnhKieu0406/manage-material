package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.CustomerAddress;

import java.util.List;

import static vn.com.devmaster.service.managematerial.untils.Sql.CUSTOMER_ADDRESS_BY_ID_CUSTOMER;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

    CustomerAddress findAllById(Integer id);
    @Query(value= "select cd from CustomerAddress cd \n" +
            "           where cd.idCustomer.id = :id" )
    List<CustomerAddress> findAllByIdCustomer(Integer id);

    @Query(value = "select cd from CustomerAddress cd " +
            "where cd.idCustomer.id = :idCus and cd.id = :idCusAdd")
    List<CustomerAddress> findAllByIdCustomerAndId(Integer idCusAdd,Integer idCus);

}