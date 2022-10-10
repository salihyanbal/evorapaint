package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ShoppingCartItemService;
import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shoppingcartitems")
@CrossOrigin
public class ShoppingCartItemsController {

    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public ShoppingCartItemsController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ShoppingCartItem shoppingCartItem){
        return ResponseEntity.ok(this.shoppingCartItemService.add(shoppingCartItem));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody ShoppingCartItem shoppingCartItem){
        return ResponseEntity.ok(this.shoppingCartItemService.update(shoppingCartItem));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ShoppingCartItem shoppingCartItem){
        return ResponseEntity.ok(this.shoppingCartItemService.delete(shoppingCartItem));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.shoppingCartItemService.getAll());
    }

    @GetMapping("/getallbyshoppingcartid")
    public ResponseEntity<?> getAllByShoppingCartId(@RequestParam int shoppingCartId){
        return ResponseEntity.ok(this.shoppingCartItemService.getAllByShoppingCartId(shoppingCartId));
    }

    @GetMapping("/getallwithimagebyshoppingcartid")
    public ResponseEntity<?> getAllWithImageByShoppingCartId(@RequestParam int shoppingCartId){
        return ResponseEntity.ok(this.shoppingCartItemService.getAllWithImageByShoppingCartId(shoppingCartId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.shoppingCartItemService.getById(id));
    }
}
