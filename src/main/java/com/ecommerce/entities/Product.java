package com.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ProductCategory productCategory;

    private double price;


    private int quantity;


    private String description;


    private String imageUrl;

    private String productName;
}
