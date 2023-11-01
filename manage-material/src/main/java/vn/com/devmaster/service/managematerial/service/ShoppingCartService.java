package vn.com.devmaster.service.managematerial.service;

import vn.com.devmaster.service.managematerial.dommain.CartItem;

import javax.transaction.Transactional;
import java.util.Collection;
@Transactional
public interface ShoppingCartService {
    void add(CartItem item);

    void remove(Integer id);

    CartItem update(Integer id, int qty);

    void clear();

    Collection<CartItem> getAllItem();

    int getCount();

    double getAmount();
}
