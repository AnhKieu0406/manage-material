package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.repository.CrudRepository;
import vn.com.devmaster.service.managematerial.dommain.Customer;



public interface CustomerDao extends CrudRepository<Customer,Integer> {

}
