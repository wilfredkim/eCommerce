package com.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {

    private String cellphone;


    private String emailAddress;

    private String postalAddress;

    private String town;


    private String postalCode;


    private String building;


    private String road;

}
