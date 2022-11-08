package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.models.Category;
import com.example.demo.models.Customer;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Product;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    SecurityServices securityServices;

    @GetMapping("/admin")
    public String adminPage(Model model){
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        return "/admin/home.html";
    }

    /* products controll */
    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Product> products = productRepo.getAll();
        model.addAttribute("products", products);
        return "/admin/product/products.html";
    }

    @GetMapping("/admin/products/addProduct")
    public String addProduct(Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Category> categories = categoryRepo.getCategories();
        model.addAttribute("categories", categories);
        Product product = new Product();
        model.addAttribute("product", product);
        return "/admin/product/addProduct.html";
    }

    @GetMapping("/admin/products/fetchReviews/{id}")
    public String getReviews(@PathVariable("id")int id, Model model){
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Product> productreviews=reviewRepository.getallproductreviewes(id);
        for(int i=0;i<productreviews.size();i++){
            String custname=customerRepository.getCustomerbyID(productreviews.get(i).getCategoryId()).getUserName();
            productreviews.get(i).setTitle(custname);
        }
        model.addAttribute("reviews",productreviews);
        return "/admin/review/reviews.html";
    }

    @PostMapping("/admin/products/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        productRepo.createProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/updateProduct/{productId}")
    public String updateProduct(@PathVariable("productId") int productId, Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Category> categories = categoryRepo.getCategories();
        Product product = productRepo.getProductById(productId);
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "/admin/product/updateProduct";
    }

    @PostMapping("/admin/products/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        productRepo.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId, Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        Product product = productRepo.getProductById(productId);
        productRepo.deleteProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Category> categories = categoryRepo.getCategories();
        model.addAttribute("categories", categories);
        return "/admin/category/categories";
    }

    @GetMapping("/admin/categories/addCategory")
    public String addCategory(Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        Category category = new Category();
        model.addAttribute("category", category);
        return "/admin/category/addCategory";
    }

    @PostMapping("/admin/categories/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        categoryRepo.createCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/updateCategory/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") int categoryId, Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        Category category = categoryRepo.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "/admin/category/updateCategory";
    }

    @PostMapping("/admin/categories/updateCategory")
    public String updateCategory(@ModelAttribute("category") Category category) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        categoryRepo.updateCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") int categoryId, Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        categoryRepo.deleteCategoryById(categoryId);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/customers")
    public String getCustomers(Model model) {
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<Customer> customers=customerRepository.getUsers();
        model.addAttribute("customers",customers);
        return "/admin/customer/customers.html";
    }

    @GetMapping("/admin/customers/fetchOrders/{id}")
    public String getOrders(@PathVariable("id") int id, Model model){
        Customer customer=securityServices.findLoggedInCustomer();
        if(customer==null || !customer.getRole().equals(customer.getRole())){
            return "redirect:/index";
        }
        List<OrderItem> orderItems=orderItemRepository.allOrders(id);
        model.addAttribute("orders",orderItems);
        return "/admin/customer/order.html";
    }

}
