package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.devmaster.service.managematerial.dommain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}