package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.models.Customer;
import com.example.demo.models.OrderItem;
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
    private OrderItemRepository orderItemRepository;

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
//        List<Product> r2=reviewRepository.getProductReviewJoin(id);
        Customer d=customerRepository.getCustomerbyID(id);
//        model.addAttribute("customer",d);
        model.addAttribute("review",r);
        float sum= 0.0F;
        for(int i=0;i<r.size();i++){
            sum=sum+r.get(i).getRating();
        }
        if(r.isEmpty()){sum=sum;model.addAttribute("rating","No rating yet!!");}
        else{sum=sum/(r.size());model.addAttribute("rating",sum);}
        List<Review> userreview=new ArrayList<>();
        List<OrderItem> orderbyuser=new ArrayList<>();
        Customer customerloggedin=securityServices.findLoggedInCustomer();
        if(customerloggedin!=null){
            userreview=reviewRepository.gethisreview(id,customerloggedin.getCustomerId());
            orderbyuser=orderItemRepository.searchinOrder(id,customerloggedin.getCustomerId());
        }
//        if(userreview)
//        System.out.println(p);
        model.addAttribute("userreview",userreview);
        model.addAttribute("orderbyuser",orderbyuser);
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
        return "redirect:/Product/{id}";
    }

}
