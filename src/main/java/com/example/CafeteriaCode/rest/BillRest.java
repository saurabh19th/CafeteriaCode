package com.example.CafeteriaCode.rest;

import java.util.Map;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.CafeteriaCode.POJO.Bill;



@RequestMapping(path="/bill")

public interface BillRest {

    @PostMapping(path="/add")
    ResponseEntity<String> addBill(@RequestBody Map<String,String> requestMap);



    @GetMapping(path="/get")
    ResponseEntity<List<Bill>> getAllBill();



    @GetMapping(path="/getBillById/{id}")
    ResponseEntity<List<Bill>> getBillById(@PathVariable Integer id);



    @PostMapping(path="/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable Integer id);
}
