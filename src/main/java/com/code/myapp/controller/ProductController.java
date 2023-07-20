package com.code.myapp.controller;

import com.code.myapp.model.Product;
import com.code.myapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
public class ProductController {

    private final ProductService productService;

    private static final String SIMILAR_IDS_URL = "http://mock-server:3001/product/{id}/similarids";
    private static final String PRODUCT_URL = "http://mock-server:3001/product/{id}";

    public ProductController(ProductService productService) {
            this.productService = productService;
    }

   @GetMapping("/product")
    public String getallProduct(Model model) {
        model.addAttribute("product", productService.getallProduct());
        return "product";
    }


    @GetMapping("/product/{productId}")
    public String getProduct(@PathVariable("productId") Long id,Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "products";
    }

    @GetMapping("/product/{productId}/similar")
    public String productSimilar(@PathVariable("productId") Long id,Model model) {
        model.addAttribute("product", productService.productSimilar(id));
       return "productSimilarids";
    }

   //@GetMapping("/product/{productId}/similar")
    //public List<Product> productSimilar(@PathVariable("productId") Long id) {
     //   return productService.productSimilar(id);
    //}

}
