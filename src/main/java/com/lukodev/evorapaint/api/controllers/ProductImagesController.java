package com.lukodev.evorapaint.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukodev.evorapaint.business.abstracts.ProductImageService;
import com.lukodev.evorapaint.entities.concretes.ProductImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/productimages")
@CrossOrigin
public class ProductImagesController {

    private ProductImageService productImageService;
    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;

    @Autowired
    public ProductImagesController(ProductImageService productImageService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.productImageService = productImageService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(MultipartHttpServletRequest request){
        ProductImage productImage = new ProductImage();
        MultipartFile file = request.getFile("file");
        try {
             productImage = objectMapper.readValue(request.getParameter("productImage"), ProductImage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(this.productImageService.add(productImage,file));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(MultipartHttpServletRequest request){
        ProductImage productImage = new ProductImage();
        MultipartFile file = request.getFile("file");
        try {
            productImage = objectMapper.readValue(request.getParameter("productImage"), ProductImage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(this.productImageService.update(productImage,file));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ProductImage productImage){
        return ResponseEntity.ok(this.productImageService.delete(productImage));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.productImageService.getAll());
    }

    @PostMapping("/getallbyproductids")
    public ResponseEntity<?> getAllByProductIds(@RequestBody List<Integer> productIds){
        return ResponseEntity.ok(this.productImageService.getAllByProductIds(productIds));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.productImageService.getById(id));
    }

    @GetMapping("/getbyproductid")
    public ResponseEntity<?> getByProductId(@RequestParam int productId){
        return ResponseEntity.ok(this.productImageService.getByProductId(productId));
    }
}
