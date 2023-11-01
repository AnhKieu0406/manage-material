package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.CartItem;
import vn.com.devmaster.service.managematerial.repository.ProductRepository;
import vn.com.devmaster.service.managematerial.service.ShoppingCartService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class ShoppingCartImpl implements ShoppingCartService {
    @Autowired
    ProductRepository productRespon;
    @Autowired
    HttpSession session;
    Map<Integer, CartItem> maps= new HashMap<>();
    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getId());
        if(cartItem == null){
            maps.put(item.getId(),item);
        }else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }
    @Override
    public void remove(Integer id){
        maps.remove(id);
    }
    @Override
    public CartItem update(Integer id, int qty){
        CartItem  cartItem= maps.get(id);
        cartItem.setQuantity(qty);
        return cartItem;
    }
    @Override
    public void clear(){
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }
    @Override
    public int getCount(){
        return maps.values().size();
    }
    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> (item.getQuantity() * item.getPrice())).sum();
    }

}
