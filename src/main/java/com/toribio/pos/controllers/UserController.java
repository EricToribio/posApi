package com.toribio.pos.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toribio.pos.models.LoginUser;
import com.toribio.pos.models.NewUser;
import com.toribio.pos.service.UserService;

@CrossOrigin(origins= "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/new/user")
    public String newUser(@RequestBody NewUser newUser) {
    	System.out.print(newUser);
    return "";
    }
    
    @GetMapping("/api/sign/in")
    public ResponseEntity<?> signIn(@RequestParam String email, String password) {
        LoginUser potentialUser = new LoginUser();
        potentialUser.setEmail(email);
        potentialUser.setPassword(password);
        String loginResponse = userService.login(potentialUser);
        if(loginResponse == "Invalid Email or Password" || loginResponse == "No User Found"){
            Map<String, String> map = new HashMap<String, String>();
    	    map.put("error","Invalid Email or Password");
            return new ResponseEntity<>(map,HttpStatus.valueOf(206));
        }
        Map<String, String> map = new HashMap<String, String>();
    	map.put("user",loginResponse);
            return  new ResponseEntity<>(map,HttpStatus.valueOf(200));
    }

    	
    

    @GetMapping("/api/test")
    public Map<String,String> test(){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("dog", "type of animal");
        return map;
    }
}
