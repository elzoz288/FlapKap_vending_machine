package com.example.FlapKap.s_project.controller;

import com.example.FlapKap.s_project.model.Product;
import com.example.FlapKap.s_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
   private final ProductService productService;

   @GetMapping("/get/All")
   public ResponseEntity<List<Product>>getAllProduct(){
       return new ResponseEntity<>
               (productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<String>getByName(@PathVariable String name){
        return new ResponseEntity<>
                (productService.getByName(name), HttpStatus.OK);
    }

    @PutMapping("/prime/add/{name}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product,@PathVariable String name) {
             return new ResponseEntity<>(productService.addProduct(product,name)
                     ,HttpStatus.OK);
    }
    @PutMapping("/prime/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable Integer id) {
        return new ResponseEntity<>(productService.updateProduct(product,id),HttpStatus.OK);
    }

    @PutMapping("/prime/delete/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name) {
        return new ResponseEntity<>(productService.deleteProduct(name),HttpStatus.OK);
    }

}
