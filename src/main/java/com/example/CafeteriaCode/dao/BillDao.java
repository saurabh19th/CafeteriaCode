package com.example.CafeteriaCode.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;



import com.example.CafeteriaCode.POJO.Bill;

public interface BillDao extends JpaRepository<Bill, Integer>{
    @Modifying
    @Transactional

    List<Bill> getBillById(@Param("id")Integer id);
}
