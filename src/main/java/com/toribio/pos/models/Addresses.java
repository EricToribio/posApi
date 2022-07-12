package com.toribio.pos.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="addresses")
public class Addresses {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotEmpty(message="Street is required")
@Size(min=5,message="Enter a valid street")
private String street;

@NotEmpty(message = "City is required")
private String city;

@NotEmpty(message = "City is required")
private String state;

@NotEmpty(message = "Zip code is required")
@Size(min=5,max=5,message = "Enter a valid zip code")
private Integer zipCode;

@OneToMany(mappedBy="address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

}