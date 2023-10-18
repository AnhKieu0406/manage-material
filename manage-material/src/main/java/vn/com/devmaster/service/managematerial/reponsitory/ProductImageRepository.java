package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.ProductImage;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}