package vn.com.devmaster.service.managematerial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}