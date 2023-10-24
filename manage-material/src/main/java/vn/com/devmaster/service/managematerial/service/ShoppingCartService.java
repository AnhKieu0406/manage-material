package vn.com.devmaster.service.managematerial.service;

import vn.com.devmaster.service.managematerial.dommain.CartItem;

import java.util.Collection;

public interface ShoppingCartService {
     void addCartItem(CartItem item);
     void remove(int id);
      CartItem  update(int proId,int qty);
     void clear();
     Collection<CartItem> getAllCartItem();
     int getCount();
      double totalAmount();
}
