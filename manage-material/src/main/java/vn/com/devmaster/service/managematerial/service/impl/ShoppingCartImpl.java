package vn.com.devmaster.service.managematerial.service.impl;

import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.CartItem;
import vn.com.devmaster.service.managematerial.service.ShoppingCartService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class ShoppingCartImpl implements ShoppingCartService {
    Map<Integer, CartItem> maps = new HashMap<>();

    @Override
    public void addCartItem(CartItem item){
        CartItem cartItem = maps.get(item.getProduct().getId());
        if (cartItem ==null){
            maps.put(item.getProduct().getId(), item);

        }else {
            cartItem.setQty(item.getQty()+1);
        }
    }
    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public  CartItem  update(int proId,int qty){
        CartItem cartItem = maps.get(proId);
        cartItem.setQty(qty);
        return cartItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllCartItem(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public  double totalAmount(){
        return maps.values().stream()
                .mapToDouble(item-> item.getPrice()* item.getQty())
                .sum();
    }
}
