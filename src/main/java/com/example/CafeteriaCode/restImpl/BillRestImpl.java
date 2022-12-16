package com.example.CafeteriaCode.restImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;



import com.example.CafeteriaCode.POJO.Bill;
import com.example.CafeteriaCode.constants.CafeConstants;
import com.example.CafeteriaCode.rest.BillRest;
import com.example.CafeteriaCode.service.BillService;
import com.example.CafeteriaCode.utils.CafeUtils;
@RestController



public class BillRestImpl implements BillRest{
    @Autowired
    BillService billService;



    @Override
    public ResponseEntity<String> addBill(Map<String, String> requestMap) {
        try {
            return billService.addBill(requestMap);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<Bill>> getAllBill() {
        try {
            ResponseEntity<List<Bill>> billList=billService.getAllBill();
            return billList;
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Bill>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<Bill>> getBillById(Integer id) {
        try {
            return billService.getBillById(id);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Bill>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        try {
            return billService.deleteById(id);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }






}