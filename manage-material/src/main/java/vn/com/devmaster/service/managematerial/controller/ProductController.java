package vn.com.devmaster.service.managematerial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.devmaster.service.managematerial.dommain.*;
import vn.com.devmaster.service.managematerial.dto.CategoryDto;
import vn.com.devmaster.service.managematerial.dto.ProductDto;
import vn.com.devmaster.service.managematerial.projection.ProductByClassId;
import vn.com.devmaster.service.managematerial.repository.CategoryRepository;
import vn.com.devmaster.service.managematerial.repository.ProductRepository;
import vn.com.devmaster.service.managematerial.service.CategoryService;
import vn.com.devmaster.service.managematerial.service.ParamService;
import vn.com.devmaster.service.managematerial.service.ProductService;
import vn.com.devmaster.service.managematerial.service.impl.ShoppingCartImpl;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ProductController {
    final
    CategoryService categorySer;

    final
    ProductService productSer;

    final
    ProductRepository productRepo;


    final
    ShoppingCartImpl shoppingCart;

    final
    ParamService paramService;
    private final CategoryRepository categoryRepository;

    public ProductController(CategoryService categorySer, ProductService productSer, ProductRepository productRepo, ShoppingCartImpl shoppingCart, ParamService paramService,
                             CategoryRepository categoryRepository) {
        this.categorySer = categorySer;
        this.productSer = productSer;
        this.productRepo = productRepo;
        this.shoppingCart = shoppingCart;
        this.paramService = paramService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/list_product")
    public String showListProduct(String name, Model model) {
        model.addAttribute("products", productSer.findAllProduct());
        model.addAttribute("categories", categorySer.findAll());

        return "/features/product/view_product";
    }

    /*
     *
     * Danh sách product
     * By id category
     */
    @GetMapping("/list_product/category/{id}")
    public String shopByCategory(@PathVariable(value = "id") Integer id, Model model) {
        List<Category> category = categorySer.findAll();
        List<Product> list = productRepo.findAllByCategory_Id(id);

        model.addAttribute("categories", category);
        model.addAttribute("products", list);

        return "/features/product/view_product";
    }


    /*
     * chi tiết sản phẩm by Id product
     *
     */
    @GetMapping("/findById/{id}")
    public String findProductById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("productId", productRepo.findAllById(id));
        model.addAttribute("findbyID", productRepo.getProduct(id));
        return "/features/product/product";
    }


    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categorySer.findAll());
        return "/features/product/upload";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(name = "product") Product product
            , @RequestParam("file") MultipartFile multipartFile
            , @RequestParam("idCategory") Integer idCategory
            , Model model
            , HttpSession session) {
        Customer customer = (Customer) session.getAttribute("admin");
        Category category = categoryRepository.findAllById(idCategory);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "upload/";
        product.setImage(fileName);
        product.setIdcategory(category);
        product.setCreatedDate(new Date().toInstant());
        product.setCreatedBy(customer.getName());
        product.setIsactive((byte) 1);
        paramService.save(multipartFile, uploadDir);
        productSer.save(product);

        return "redirect:/api/list_product";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {

        productSer.deleteById(id);
        return "";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute(name = "product") Product product
            , @PathVariable("id") Integer id
            , Model model
            , HttpSession session) {
        Customer customer = (Customer) session.getAttribute("admin");
        model.addAttribute("categories", categorySer.findAll());
        Optional<Product> product1 = productSer.findById(id);
        if (product1.isPresent()) {
            model.addAttribute("product1", product1.get());

        } else {
            model.addAttribute("product", new Product());
        }


        return "/features/product/upload";
    }

    @PostMapping("/save/{id}")
    public String saveOrUpdate(@ModelAttribute(name = "product") Product product
            , Model model
            , @RequestParam("file") MultipartFile multipartFile
            , @PathVariable(name = "id") Integer id
            , @RequestParam("idCategory") Integer idCategory
            , HttpSession session) {

        Customer customer = (Customer) session.getAttribute("admin");
        Category category = categoryRepository.findAllById(idCategory);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "upload/";
        product.setImage(fileName);
        paramService.save(multipartFile, uploadDir);
        Optional<Product> product1 = productSer.findById(id);

        if (product1.isPresent()) {
            model.addAttribute("product1", product1.get());
            product.setCreatedDate(product1.get().getCreatedDate());
            product.setUpdatedDate(new Date().toInstant());
            product.setCreatedBy(product1.get().getCreatedBy());
            product.setUpdatedBy(customer.getName());
            product.setIsactive(product1.get().getIsactive());
            product.setIdcategory(product1.get().getIdcategory());
            productRepo.save(product);
            return "redirect:/api/list_product";
        } else {
            model.addAttribute("product", new Product());
        }

        return "/features/product/view_product";

    }

    @GetMapping("/off/{id}")
    public String offProduct(@PathVariable("id")Integer id){
        Product product = productSer.findAllById(id);
        product.setIsactive((byte) 0);
        productRepo.save(product);
        return "redirect:/api/list_product";

    }
    @GetMapping("/on/{id}")
    public String onProduct(@PathVariable("id")Integer id){
        Product product = productSer.findAllById(id);
        product.setIsactive((byte) 1);
        productRepo.save(product);
        return "redirect:/api/list_product";
    }


}
