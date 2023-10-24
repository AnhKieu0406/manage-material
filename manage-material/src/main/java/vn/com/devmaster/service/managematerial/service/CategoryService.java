package vn.com.devmaster.service.managematerial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.mapper.CategoryMapper;
import vn.com.devmaster.service.managematerial.reponsitory.CategoryRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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

    public void addCategory(Category category) throws ParseException {
        Category categories =new Category();
//        ZoneId hcm = ZoneId.of("Asia/Ho_Chi_Minh");
        Instant dateS = categories.getCreatedDate();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(dateS));

        categoryRepo.save(categories);

    }
}
