package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dommain.CustomerAddress;
import vn.com.devmaster.service.managematerial.repository.CustomerAddressRepository;
import vn.com.devmaster.service.managematerial.service.CustomerService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ShoppingCartImpl shoppingCart;
    @Autowired
    CustomerAddressRepository addressRepository;

    @GetMapping("/myAccount/{username}")
    public String TaiKhoanCuaToi(@PathVariable("username")String username,
                                 Model model){
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("my_account",customer);

        List<CustomerAddress> customerAddress = addressRepository.findAllByIdCustomer(customer.getId());
        model.addAttribute("myAddress",customerAddress);

        model.addAttribute("cartItem", shoppingCart.getAllItem());
        model.addAttribute("Total", shoppingCart.getAmount());
        model.addAttribute("cartCount", shoppingCart.getCount());
        return "/layout/my_account";
    }
}
