package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dommain.Customer;
import vn.com.devmaster.service.managematerial.dto.CategoryDto;
import vn.com.devmaster.service.managematerial.repository.CategoryRepository;
import vn.com.devmaster.service.managematerial.service.CategoryService;
import vn.com.devmaster.service.managematerial.service.ParamService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/cate")
public class CategoryController {
    @Autowired
    CategoryService categorySer;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ParamService paramService;

    // find all Category
    @GetMapping("/findAll")
    public String getAllCat(Model model) {
        model.addAttribute("categories", categorySer.findAll());
        return "/features/category/view_category";
    }


    //add category
    @GetMapping("/add")
    public String newCateGory(Model model) {
        model.addAttribute("category", new Category());
        return "/features/category/category_add";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute(name = "category") Category category,
                               @RequestParam("file") MultipartFile multipartFile,
                               Model model,
                               HttpSession session) {
        // thêm ảnh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "upload/";
        category.setIcon(fileName);
        paramService.save(multipartFile, uploadDir);
        // cập nhật tên người thêm
        Customer customer = (Customer) session.getAttribute("admin");
        category.setCreatedDate(new Date().toInstant());
        category.setCreatedBy(customer.getName());
        session.setAttribute("saveFile", paramService.save(multipartFile, uploadDir));

        categorySer.save(category);
        return "redirect:/cate/findAll";
    }


    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        categorySer.deleteById(id);
        return "";
    }

//    @GetMapping("/edit/{id}")
//    public String editCate(@PathVariable("id")Integer id, Model model){
//        Optional<Category> category = categoryRepository.findById(id);
//        model.addAttribute("category",category);
//        model.addAttribute("id", id);
//        return "/features/category/category_add";
//    }
//    @PostMapping("/save/{id}")
//    public  String saveEditCar(@ModelAttribute("category") Category category) {
//        categorySer.save(category);
//        return "redirect:/cate/findAll";
//    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute(name = "category") Category category
            ,@PathVariable(name = "id") Integer id
            , Model model
            , HttpSession session) {
        Customer customer = (Customer) session.getAttribute("admin");
//
        Optional<Category> category1 = categorySer.findById(id);
        if (category1.isPresent()) {
            model.addAttribute("category1", category1.get());
        } else {
            model.addAttribute("category", new Category());
        }
        return "/features/category/category_add";

    }

    @PostMapping("/save/{id}")
    public String saveOrUpdate(@ModelAttribute(name = "category") Category category
            , Model model
            , @RequestParam("file") MultipartFile multipartFile
            , @PathVariable(name = "id") Integer id
            , HttpSession session) {

        // cập nhật tên người sửa
        Customer customer = (Customer) session.getAttribute("admin");

        // cập nhật ảnh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "upload/";
        category.setIcon(fileName);
        paramService.save(multipartFile, uploadDir);
        //
        Optional<Category> category1 = categorySer.findById(id);
        if (category1.isPresent()) {
            model.addAttribute("category1", category1.get());
            category.setUpdatedBy(customer.getName());
            category.setUpdatedDate(new Date().toInstant());
            category.setCreatedDate(category1.get().getCreatedDate());
            category.setCreatedBy(category1.get().getCreatedBy());
            categoryRepository.save(category);
            return "redirect:/cate/findAll";
        } else {
            model.addAttribute("category", new Category());
        }
        return "redirect:/cate/findAll";
    }
}
