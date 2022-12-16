package com.example.CafeteriaCode.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import com.example.CafeteriaCode.POJO.Bill;
import com.example.CafeteriaCode.POJO.Product;
import com.example.CafeteriaCode.constants.CafeConstants;
import com.example.CafeteriaCode.dao.BillDao;
import com.example.CafeteriaCode.service.BillService;
import com.example.CafeteriaCode.utils.CafeUtils;



@Service
public  class BillServiceImpl implements BillService{
    @Autowired
    BillDao billDao;



    @Override //(Adding A Bill)
    public ResponseEntity<String> addBill(Map<String, String> requestMap) {
        try {
//        if(jwtFilter.isAdmin()) {
            if(validateBillMap(requestMap,false)) {
                billDao.save(getBillFromMap(requestMap,true));
                return CafeUtils.getResponseEntity("Bill Added Successfully", HttpStatus.OK);
            }
//            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);

//        }else
//            return CafeUtils.getResponseEntity(CafeConstants,UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateBillMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("name")) {
            if(requestMap.containsKey("id") && validateId) {
                return true;
            }else if(!validateId) {
                return true;
            }
        }
        return false;
    }



    private Bill getBillFromMap(Map<String, String> requestMap, boolean isAdd) {
        Product product = new Product();
        product.setId(Integer.parseInt((String) requestMap.get("productId")));
//    product.setPrice(Integer.parseInt((String) requestMap.get("productPrice")));
        Bill bill = new Bill();
        if(isAdd) {
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactnumber"));
            bill.setPaymentMethod((String) requestMap.get("payementmethod"));
            bill.setCreatedBy((String) requestMap.get("createdBy"));
            bill.setProduct(product);
        }
        return bill;

    }



    @Override//(Get All Bills)
    public ResponseEntity<List<Bill>> getAllBill() {
        List<Bill> list=new ArrayList<>();
        list=billDao.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }





    @Override//(Delete Bill By Id)
    public ResponseEntity<String> deleteById(Integer id) {
        try {
            Optional<Bill> optional = billDao.findById(id);
            if(!optional.isEmpty()) {
                billDao.deleteById(id);
                return CafeUtils.getResponseEntity("Bill Deleted", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Bill Not Exist", HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<Bill>> getBillById(Integer id) {
        try {
            return new ResponseEntity<>(billDao.getBillById(id),HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }







}