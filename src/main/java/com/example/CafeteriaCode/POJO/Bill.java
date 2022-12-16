package com.example.CafeteriaCode.POJO;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import javax.persistence.Table;



import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="bill")



public class Bill implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;



    @Column(name="name")
    private String name;



    @Column(name="email")
    private String email;



    @Column(name="contactnumber")
    private String contactNumber;



    @Column(name="paymentmethod")
    private String paymentMethod;





    @Column(name="createdby")
    private String createdBy;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name ="product_fk",nullable=false)
    private Product product;




    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getContactNumber() {
        return contactNumber;
    }



    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }



    public String getPaymentMethod() {
        return paymentMethod;
    }



    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }





    public String getCreatedBy() {
        return createdBy;
    }



    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



    public Product getProduct() {
        return product;
    }



    public void setProduct(Product product) {
        this.product = product;
    }



    public static long getSerialversionuid() {
        return serialVersionUID;
    }




    public Bill() {
        super();

    }





}