package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dto.CategoryDto;
import vn.com.devmaster.service.managematerial.mapper.CategoryMapper;
import vn.com.devmaster.service.managematerial.reponsitory.CategoryRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    CategoryMapper categoryMap;

    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    public void addCategory(CategoryDto category){
        Category categories =categoryMap.toEntity(category);
        categoryRepo.save(categories);

    }
}
