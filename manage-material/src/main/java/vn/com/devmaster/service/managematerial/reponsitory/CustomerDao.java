package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Customer;



public interface CustomerDao extends CrudRepository<Customer,Integer> {

}
