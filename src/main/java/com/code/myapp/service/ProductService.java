package com.code.myapp.service;

import com.code.myapp.model.Product;
import com.code.myapp.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo repository;

    public ProductService(ProductRepo repository) {

        this.repository = repository;
    }

    public List<Product> getallProduct(){
        return repository.getallProduct();
    }

   public List<Product> productSimilar(Long id) { return repository.productSimilar(id);}

    public Product getProduct(Long id) {return repository.getProduct(id);}



}
