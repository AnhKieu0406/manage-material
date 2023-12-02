package vn.com.devmaster.service.managematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managematerial.dommain.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findAllById(Integer id);
}