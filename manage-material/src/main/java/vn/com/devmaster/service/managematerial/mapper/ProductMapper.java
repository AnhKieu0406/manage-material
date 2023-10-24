package vn.com.devmaster.service.managematerial.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.dto.ProductDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductMapper implements EntityMapper<Product, ProductDto>{
   @Autowired
   CategoryMapper categoryMapper;

   @Autowired
   OrderdetailMapper orderdetailMapper;

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .notes(dto.getNotes())
                .image(dto.getImage())
                .price(dto.getPrice())
                .quatity(dto.getQuatity())
                .createdDate(LocalDateTime.from(dto.getCreatedDate()))
                .updatedDate(LocalDateTime.from(dto.getUpdatedDate()))
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .isactive(dto.getIsactive())
                .idcategory(categoryMapper.toEntity(dto.getCategory()))
//                .ordersDetails(orderdetailMapper.toEntity(dto.getOrdersDetails()))
                .build();
        return product;
    }

    @Override
    public List<Product> toEntity(List<ProductDto> d) {
        List<Product> list = new ArrayList<>();
        d.forEach(dto -> {
            list.add(toEntity(dto));
        });
        return null;
    }

    @Override
    public ProductDto toDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .notes(product.getNotes())
                .image(product.getImage())
                .price(product.getPrice())
                .quatity(product.getQuatity())
                .createdDate(LocalDate.from(product.getCreatedDate()))
                .updatedDate(LocalDate.from(product.getUpdatedDate()))
                .createdBy(product.getCreatedBy())
                .updatedBy(product.getUpdatedBy())
                .isactive(product.getIsactive())
                .category(categoryMapper.toDto(product.getIdcategory()))
//                .ordersDetails(orderdetailMapper.toDto(product.getOrdersDetails()))
                .build();
        return productDto;
    }

    @Override
    public List<ProductDto> toDto(List<Product> e) {
        List<ProductDto> list = new ArrayList<>();
        e.forEach(product -> {
            list.add(toDto(product));
        });

        return list;
    }
}
