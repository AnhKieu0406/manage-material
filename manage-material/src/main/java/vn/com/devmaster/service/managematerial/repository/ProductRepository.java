package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.projection.ProductCategoryName;
import vn.com.devmaster.service.managematerial.projection.ProductImageById;
import vn.com.devmaster.service.managematerial.untils.Sql;

import java.util.List;

import static vn.com.devmaster.service.managematerial.untils.Sql.*;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findAllById(Integer id);

    @Query(value = Sql.PRODUCT_IMAGE_BY_ID ,nativeQuery = true)
    List<ProductImageById> getProduct(Integer id);


//    @Query(value = PRODUCT_CATEGORY_NAME ,nativeQuery = true)
//    List<ProductCategoryName> getProductCategory(@Param("name") String name);

    @Query(value = "select p from Product  p where  p.idcategory.id = :id")
    List<Product> findAllByCategory_Id(Integer id);

    @Query(value = PRODUCT_CATEGORY_NAME,nativeQuery = true)
    List<ProductCategoryName> findProductCategory();


    @Query(value = "select p from Product  p where p.isactive= 1 ")
    List<Product> findByIsActive();
}