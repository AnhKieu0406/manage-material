package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.repository.CategoryRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRespon;

    public Category save(Category category)  {
        return  categoryRespon.save(category);
    }

    public List<Category> saveAll(List<Category> entities) {

        return (List<Category>) categoryRespon.saveAll(entities);
    }

    public Optional<Category> findById(Integer integer) {
        return categoryRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return categoryRespon.existsById(integer);
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRespon.findAll();
    }

    public List<Category> findAllById(List<Integer> integers) {
        return (List<Category>) categoryRespon.findAllById(integers);
    }

    public long count() {
        return categoryRespon.count();
    }

    public void deleteById(Integer integer) {
        categoryRespon.deleteById(integer);
    }

    public void delete(Category entity) {
        categoryRespon.delete(entity);
    }

//    public void deleteAllById(List<Integer> integers) {
//        categoryRespon.deleteAllById(integers);
//    }
//
//    public void deleteAll(List<Category> entities) {
//        categoryRespon.deleteAll(entities);
//    }
//
//    public void deleteAll() {
//        categoryRespon.deleteAll();
//    }



//    public Category findById(Integer id) {
//        Category category = categoryRepo.findById(id);
//        return category;
//    }
}
