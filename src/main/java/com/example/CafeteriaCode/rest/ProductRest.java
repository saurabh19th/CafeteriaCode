package com.example.CafeteriaCode.rest;


import com.example.CafeteriaCode.wrapper.ProductWrapper;
import com.sun.security.jgss.InquireSecContextPermission;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path="/product")
public interface ProductRest {

    @PostMapping(path="/add")
    ResponseEntity<String> addNewProduct(@RequestBody Map<String,String> requestMap);

    @GetMapping(path="/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PutMapping(path="/update")
    ResponseEntity<String> updateProduct(Map<String, String> requestMap);

    @PostMapping(path="/delete")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    @PostMapping(path = "/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody Map<String,String> requestMap);

    @GetMapping(path="/getByCategory/{id}")
    ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);

    @GetMapping(path = "/getById/{id}")
    ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);
}
