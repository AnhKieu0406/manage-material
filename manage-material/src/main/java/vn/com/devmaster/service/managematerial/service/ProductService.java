package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.mapper.ProductMapper;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.reponsitory.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductMapper productMap;

    public List<ProductDto> findAllProduct(){
        List<Product> list = productRepo.findAll();
        return productMap.toDto(list);
    }

    public void save(ProductDto product){
        Product product1 = productMap.toEntity(product);
         productRepo.save(product1);
    }
    public void save(List<ProductDto> list){
        productRepo.saveAll(productMap.toEntity(list));
    }


//    public List<ProductDto> getAllProductsByCategoryId(Integer id){
//        List<Product> list = productRepo.findAllByCategory_Id(id);
//        return productMap.toDto(list);
//    }



}
