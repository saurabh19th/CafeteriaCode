package com.example.CafeteriaCode.service;

import java.util.List;
import java.util.Map;




import org.springframework.http.ResponseEntity;



import com.example.CafeteriaCode.POJO.Bill;



public interface BillService {



    ResponseEntity<String> addBill(Map<String, String> requestMap);



    ResponseEntity<List<Bill>> getAllBill();



    ResponseEntity<List<Bill>> getBillById(Integer id);



    ResponseEntity<String> deleteById(Integer id);
}