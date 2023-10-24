package vn.com.devmaster.service.managematerial.dto;

import lombok.*;
import vn.com.devmaster.service.managematerial.dommain.Order;
import vn.com.devmaster.service.managematerial.dommain.Product;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.OrdersDetail}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter

public class OrdersDetailDto  {
    Integer id;
    Double price;
    Integer qty;
    Product product;
    Order order;
}