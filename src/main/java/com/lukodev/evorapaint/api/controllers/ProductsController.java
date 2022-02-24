package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product){
        return ResponseEntity.ok(this.productService.add(product));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Product product){
        return ResponseEntity.ok(this.productService.update(product));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Product product){
        return ResponseEntity.ok(this.productService.delete(product));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/getallbycategoryid")
    public ResponseEntity<?> getAllByCategoryId(@RequestParam int categoryId){
        return ResponseEntity.ok(this.productService.getAllByCategoryId(categoryId));
    }

    @GetMapping("/getrandom")
    public ResponseEntity<?> getRandom(@RequestParam int amount){
        return ResponseEntity.ok(this.productService.getRandom(amount));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.productService.getById(id));
    }

}
