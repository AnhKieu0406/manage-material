package vn.com.devmaster.service.managematerial.mapper;

import org.springframework.stereotype.Component;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {
    @Override
    public Customer toEntity(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .createdDate(customerDto.getCreatedDate())
                .isactive(customerDto.getIsactive())
                .build();
        return customer;
    }

    @Override
    public List<Customer> toEntity(List<CustomerDto> d) {
        List<Customer> customers = new ArrayList<>();
        d.forEach(customerDto -> {
            customers.add(toEntity(customerDto));
        });
        return customers;
    }

    @Override
    public CustomerDto toDto(Customer customer) {

        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .createdDate(customer.getCreatedDate())
                .isactive(customer.getIsactive())
                .build();
    }

    @Override
    public List<CustomerDto> toDto(List<Customer> e) {
        List<CustomerDto> dtos = new ArrayList<>();
        e.forEach(customer -> {
            dtos.add(toDto(customer));
        });
        return dtos;
    }
}
