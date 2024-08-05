package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Product;
import com.csi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/savedata")
    public ResponseEntity<Product> saveData(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.saveData(product), HttpStatus.CREATED);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<Optional<Product>> findById(@RequestParam int prodId) {
        return new ResponseEntity<>(productService.findById(prodId), HttpStatus.OK);
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<Product>> findByName(@RequestParam String prodName) {
        return new ResponseEntity<>(productService.findByName(prodName), HttpStatus.OK);
    }

    @GetMapping("/findbyall")
    public ResponseEntity<List<Product>> findByAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updatedata")
    public ResponseEntity<Product> updateData(@RequestParam int prodId, @Valid @RequestBody Product product) {
        Product product1 = productService.findById(prodId).orElseThrow(() -> new RecordNotFoundException("Id does not exist"));

        product1.setProdPrice(product.getProdPrice());
        product1.setProdName(product.getProdName());
        product1.setProdLaunchDate(product.getProdLaunchDate());

        return new ResponseEntity<>(productService.updateData(product1), HttpStatus.CREATED);
    }

    @PatchMapping("/updateprice")
    public ResponseEntity<Product> updatePrice(@RequestParam int prodId, @RequestParam double prodPrice) {
        Product product1 = productService.findById(prodId).orElseThrow(() -> new RecordNotFoundException("Id does not exist"));

        product1.setProdPrice(prodPrice);

        return new ResponseEntity<>(productService.updateData(product1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity<String> deleteById(@RequestParam int prodId) {
        productService.deleteById(prodId);
        return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        productService.deleteAll();
        return new ResponseEntity<>("All Data deleted successfully", HttpStatus.OK);
    }
}
