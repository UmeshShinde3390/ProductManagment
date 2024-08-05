package com.csi.service;

import com.csi.model.Product;
import com.csi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product saveData(Product product) {
        return productRepo.save(product);

    }

    public Optional<Product> findById(int prodId) {
        return productRepo.findById(prodId);
    }

    public List<Product> findByName(String prodName) {
        return productRepo.findByProdName(prodName);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product updateData(Product product) {
        return productRepo.save(product);
    }

    public void deleteById(int prodId) {
        productRepo.deleteById(prodId);
    }

    public void deleteAll() {
        productRepo.deleteAll();
    }
}
