package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.mapper.ProductMapper;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.projection.ProductCategoryName;
import vn.com.devmaster.service.managematerial.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductMapper productMap;

    public List<Product> findAllProduct(){
//        List<Product> list = productRepo.findAll();
        return (List<Product>) productRepo.findAll();
    }

    public List<Product> findByIsActive(){
        return  productRepo.findByIsActive();
    }
    public Product findAllById(Integer id) {
        return productRepo.findAllById(id);
    }

    public Optional<Product> findById(Integer id){
        return  productRepo.findById(id);
    }

    public List<ProductCategoryName> findAllProductByCategory() {
        List<ProductCategoryName> findProductCategory = productRepo.findProductCategory();
        return findProductCategory;
    }


    public Product save(Product product){
        return productRepo.save(product);
    }


    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }

}
