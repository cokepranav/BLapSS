package com.example.demo.controller;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.ReviewRepository;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Productcontroller {

    @Autowired
    private SecurityServices securityServices;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @GetMapping("/SaveProduct")

    @RequestMapping(path = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@ModelAttribute String keyword , Model model){
        keyword = "Title";
        List <Product> ProductsList = productRepository.search(keyword);
        model.addAttribute("ProductsList", ProductsList);
        return  "AllProducts";
    }

    @RequestMapping(path="/SaveProduct",method={RequestMethod.POST,RequestMethod.GET})
    public String setProduct(@ModelAttribute Product product){
//        String ef=request.getParameter("title");
//        System.out.println(ef);
        if(product.getProductId()!=0){
        productRepository.createProduct(product);}
//        model.addAttribute("newProduct",product);
        return "Productform";
    }

    @PutMapping("/Product")
    public String updateProduct(@RequestBody Product product){
        productRepository.updateProduct(product);
        return "Product";
    }

    @DeleteMapping("/Product")
    public String deleteproduct(@RequestBody Product product){
        productRepository.deleteProduct(product);
        return "Product";
    }

    @GetMapping("/Products")
//    @RequestMapping()
    public String getALlpers(Model model){
        model.addAttribute("products", productRepository.getAll());
//        model.addAttribute("review",)

//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("Product");
//        modelAndView.addObject("products",p);
//        System.out.println(p);
//        return modelAndView;
        return "Product";
    }
    @GetMapping("/Product/{id}")
    public String getSingleProduct(@PathVariable("id")int id,Model model){
//        Product p=productRepository.getProductById(id);
        model.addAttribute("naam",securityServices.findLoggedInUsername());
        model.addAttribute("user",securityServices.findLoggedInCustomer());
        Product product = productRepository.getProductById(id);
        model.addAttribute("products", product);
        List<String> availableSizes = new ArrayList<String>();
        if (product.getSmallInStock() > 0) availableSizes.add("S");
        if (product.getMediumInStock() > 0) availableSizes.add("M");
        if (product.getLargeInStock() > 0) availableSizes.add("L");
        model.addAttribute("availableSizes", availableSizes);
        List<Review> r=reviewRepository.getProductReview(id);
        List<Review> r2=reviewRepository.getAvgProductReview(id);
        Customer d=customerRepository.getCustomerbyID(id);
        model.addAttribute("customer",d);
        model.addAttribute("review",r);
        model.addAttribute("reviewAvg",r2);
//        System.out.println(p);
        return "Product";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "Cart";
    }

    @PostMapping("/addToCart/{productId}")
    public String addToCart(@RequestParam Map<String, String> body,@PathVariable("productId") int productId, Model model) {
        model.addAttribute("naam",securityServices.findLoggedInUsername());
        model.addAttribute("user",securityServices.findLoggedInCustomer());
        System.out.println("CART CONTROLLER CALLED");
        Customer customer = customerRepository.getCustomerbyUsername(securityServices.findLoggedInUsername());
        System.out.println(customer.toString());
        String size = body.get("size");
        System.out.println(size);
        cartItemRepository.addToCart(customer, productId, size);
        return "redirect:/Products";
    }

}
