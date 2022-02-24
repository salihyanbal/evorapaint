package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.CategoryService;
import com.lukodev.evorapaint.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoriesController {

    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.add(category));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.update(category));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.delete(category));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.categoryService.getById(id));
    }

}
