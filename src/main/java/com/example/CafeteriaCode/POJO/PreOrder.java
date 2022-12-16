package com.example.CafeteriaCode.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "preorder")
public class PreOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "username")
    private String username;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "payment_method")
    private String paymentMethod;


}
