package com.toribio.pos.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NewUser {
	
	    @NotNull
	    @Size(min=3, message="First Name must be at least 3 characters")
	    public String firstName;
	    
	    @NotNull
	    @Size(min=3, message="First Name must be at least 3 characters")
	    public String lastName;

	    @NotNull
	    @Size(min=10,max=10,message="Enter valid phone number")
	    public Long phoneNumber;

	    @NotEmpty(message="Email is required!")
	    @Email(message="Please enter a valid email!")
	    private String email;
	    
	    @NotEmpty(message="Password is required!")
	    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	    private String password;
	    
	    @NotEmpty(message="Confirm Password is required!")
	    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	    private String confirm;
	    
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
}

	  
