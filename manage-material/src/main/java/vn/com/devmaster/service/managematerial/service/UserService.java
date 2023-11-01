package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.repository.CustomerRepository;

@Service
public class UserService {
    @Autowired
    CustomerRepository customerRepo;

    public Customer findByName(String username){
        return customerRepo.findByUsername(username);
    }

}
