package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managematerial.repository.OrderRepository;
import vn.com.devmaster.service.managematerial.service.OrderService;

@Controller
@RequestMapping("/view")
public class OrderViewController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepo;


//    @GetMapping("/listorder")
//    public String showListOrder(Model model){
//        model.addAttribute("orders",orderService.findAll());
//        return "/features/orders/listOrder";
//    }
//
//    @PostMapping("/save/{id}")
//    public String saveEditOrder(@ModelAttribute("order")OrderDto orderDto){
//        orderService.save(orderDto);
//        return "redirect:/view/listorder";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editOrder(@PathVariable("id")Integer id,Model model){
//        model.addAttribute("order",orderRepo.findById(id));
//        model.addAttribute("id", id);
//        model.addAttribute("pageTitle","id :"+id);
//        return "/features/orders/order";
//    }
}
