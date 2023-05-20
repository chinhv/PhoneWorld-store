package com.myproject.mystore.entities;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name = "cart_tb")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "total")
    private long totalPayment;
   

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    
}
