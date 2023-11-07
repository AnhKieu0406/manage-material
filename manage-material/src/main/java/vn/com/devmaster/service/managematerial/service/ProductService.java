package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.mapper.ProductMapper;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.projection.ProductCategoryName;
import vn.com.devmaster.service.managematerial.repository.ProductRepository;

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

    public Product findById(Integer id) {
        return productRepo.findAllById(id);
    }

    public List<ProductCategoryName> findAllProductByCategory() {
        List<ProductCategoryName> findProductCategory = productRepo.findProductCategory();
        return findProductCategory;
    }
//
//    public int getCountProduct(){
//        List<ProductByClassId> list = productRepo.findAllByCategory_Id()
//        List<Product> list = productRepo.findAll();
//        return list.size();
//    }



//    public List<ProductDto> getAllProductsByCategoryId(Integer id){
//        List<Product> list = productRepo.findAllByCategory_Id(id);
//        return productMap.toDto(list);
//    }



}
