package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dommain.Product;
import vn.com.devmaster.service.managematerial.dto.CategoryDto;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.projection.ProductImageById;
import vn.com.devmaster.service.managematerial.reponsitory.ProductRepository;
import vn.com.devmaster.service.managematerial.service.CategoryService;
import vn.com.devmaster.service.managematerial.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ProductController {
    @Autowired
    CategoryService categorySer;

    @Autowired
    ProductService productSer;

    @Autowired
    ProductRepository productRepo;

    // find all Category
    @GetMapping("/category/findAll")
    public String getAllCat(Model model){
        model.addAttribute("categories",categorySer.getAllCategory());
        return "/features/category/view_category";
    }


    //add category
    @GetMapping("/category/add")
    public  String getCarAdd(Model model){
        CategoryDto categoryDto = new CategoryDto();



        model.addAttribute("category",categoryDto);
        return "/features/category/category_add";
    }
    @PostMapping("/category/add")
    public  String postCarAdd(@ModelAttribute("category") CategoryDto category){
        categorySer.addCategory(category);
        return "redirect:/features/category/view_category";
    }


    @GetMapping("/listproduct")
    public String showListProduct( String name, Model model) {
        model.addAttribute("products", productSer.findAllProduct());
        model.addAttribute("categories",categorySer.getAllCategory());

        return "/features/product/view_product";
    }

    @GetMapping("/listproduct/category/{id}")
    public String shopByCategory(@PathVariable(value = "id") Integer id, Model model) {
        List<Category> category = categorySer.getAllCategory();
        List<ProductByClassId> list = productRepo.findAllByCategory_Id(id);



        model.addAttribute("categories",category);
        model.addAttribute("products", list);


        return "/features/product/view_product";
    }

    @GetMapping("/upload")
    public String showIndex(Model model) {
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories",categorySer.getAllCategory());
        return "/features/product/upload";
    }

    @PostMapping("/upload")
    public String fileupload(@RequestParam("file") MultipartFile file, Model model) throws IOException, SQLException {
        Product product = new Product();

        return "redirect:/features/product/view_product";
    }

    /*
    * chi tiết sản phẩm by Id product
    * */
    @GetMapping("/findById/{id}")
    public String findProductById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("productId", productRepo.findAllById(id));
        model.addAttribute("findbyID",productRepo.getProduct(id));
        return "/features/product/product";
    }

//    @GetMapping("/findbyid")
//    public String  byIdList(@PathVariable("id") Integer id,Model model){
//        model.addAttribute("findbyid",productRepo.getProduct(id));
//        return "/features/product/product_image";
//    }

//    public static void main(String[] args) throws ParseException {
//        String dateS = "10-08-2023";
//        ZoneId hcm = ZoneId.of("Asia/Ho_Chi_Minh");
//        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateS);
//        System.out.println(date.toInstant().atZone(hcm));
//    }
}
