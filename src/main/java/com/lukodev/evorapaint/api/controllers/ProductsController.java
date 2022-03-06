package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> add(@Valid @RequestBody Product product){
        return ResponseEntity.ok(this.productService.add(product));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Product product){
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

    @GetMapping("/getallactive")
    public ResponseEntity<?> getAllActive(){
        return ResponseEntity.ok(this.productService.getAllActive());
    }

    @GetMapping("/getallbycategoryid")
    public ResponseEntity<?> getAllByCategoryId(@RequestParam int categoryId){
        return ResponseEntity.ok(this.productService.getAllByCategoryId(categoryId));
    }

    @GetMapping("/getallbycategoryidandactive")
    public ResponseEntity<?> getAllByCategoryIdAndActive(@RequestParam int categoryId){
        return ResponseEntity.ok(this.productService.getAllByCategoryIdAndActive(categoryId));
    }

    @GetMapping("/getrandom")
    public ResponseEntity<?> getRandom(@RequestParam int amount){
        return ResponseEntity.ok(this.productService.getRandom(amount));
    }

    @GetMapping("/getrandomactive")
    public ResponseEntity<?> getRandomActive(@RequestParam int amount){
        return ResponseEntity.ok(this.productService.getRandomActive(amount));
    }

    @GetMapping("/getallactivewithimage")
    public ResponseEntity<?> getAllActiveWithImage(){
        return ResponseEntity.ok(this.productService.getAllActiveWithImage());
    }

    @GetMapping("/getallbycategoryidandactivewithimage")
    public ResponseEntity<?> getAllByCategoryIdAndActiveWithImage(@RequestParam int categoryId){
        return ResponseEntity.ok(this.productService.getRandomActive(categoryId));
    }

    @GetMapping("/getallproductswithimagebyorderid")
    public ResponseEntity<?> getAllProductsWithImageByOrderId(@RequestParam int orderId){
        return ResponseEntity.ok(this.productService.getAllProductsWithImageByOrderId(orderId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @GetMapping("/getbyidwithimage")
    public ResponseEntity<?> getByIdWithImage(@RequestParam int id){
        return ResponseEntity.ok(this.productService.getByIdWithImage(id));
    }

}
