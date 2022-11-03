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
        if(product.getProductId()!=0){
        productRepository.createProduct(product);}
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
        return "AllProducts";
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

        if(r2.isEmpty()){model.addAttribute("reviewAvg","not yet given");}
        else{model.addAttribute("reviewAvg",r2);}
        if(r.isEmpty()){
            model.addAttribute("no","No reviews yet");
        }
        else{model.addAttribute("review",r);}
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
        Product product=productRepository.getProductById(productId);
        Customer customer = customerRepository.getCustomerbyUsername(securityServices.findLoggedInUsername());
        System.out.println(customer.toString());
        String size = body.get("size");
        System.out.println(size);
        if(cartItemRepository.gethiscart(customer.getCustomerId(), productId,size)!=null){
            model.addAttribute("error_msg","Already in your cart!!");
            return "redirect:/Product/{productId}?q=Already+in+your+Cart";
        }
        productRepository.intoCart(productId,size);
        cartItemRepository.addToCart(customer, productId, size);
        return "redirect:/Products";
    }

    @PostMapping("/Product/{id}")
    public String giveReview(@RequestParam Map<String, String> newreview, @PathVariable("id") int productId,Model model){
        int i=Integer.parseInt(newreview.get("rating"));
        reviewRepository.createReview(new Review(securityServices.findLoggedInCustomer().getCustomerId(),productId,newreview.get("description"),i));
//        model.addAttribute("user",securityServices.findLoggedInCustomer());
        return "redirect:/Product/{id}";
    }

}
