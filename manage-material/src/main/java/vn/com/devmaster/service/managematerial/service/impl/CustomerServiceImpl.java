package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dto.CustomerDto;
import vn.com.devmaster.service.managematerial.reponsitory.CustomerRepository;
import vn.com.devmaster.service.managematerial.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepo;


    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());
        return  customerRepo.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepo.findByUsername(username);
    }

    @Override
    public Customer update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public Customer changePass(CustomerDto customerDto) {
        Customer customer = customerRepo.findByUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        return customerRepo.save(customer);
    }

    @Override
    public CustomerDto getCustomer(String username) {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerRepo.findByUsername(username);

        customerDto.setName(customer.getName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        return customerDto;
    }
}
