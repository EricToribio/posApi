package com.toribio.pos.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.toribio.pos.models.LoginUser;
import com.toribio.pos.models.NewUser;
import com.toribio.pos.models.User;
import com.toribio.pos.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	Instant now = Instant.now();
	byte[] secret = Base64.getDecoder().decode("o4OdCNjd8mmDN2+/nfHdIB2ZWta80foXqDx2rouL4nw=");

	public String register(NewUser newUser) {
		System.out.println(newUser);
		String emailEntered = newUser.getEmail();
		Optional<User> isUser = userRepo.findByEmail(emailEntered);
		if (!isUser.isPresent()) {
			if (newUser.getPassword().equals(newUser.getConfirm())) {
				String hashedPw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
				User user = new User();
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPassword(hashedPw);
			}
		}
		return "";
	}

	public String login(LoginUser potentialUser) {
		String emailEntered = potentialUser.getEmail();
		String passwordEntered = potentialUser.getPassword();

		Optional<User> isUser = userRepo.findByEmail(emailEntered);
		if (!isUser.isPresent()) {
			return "No User Found";
			
		}
		if (!BCrypt.checkpw(passwordEntered, isUser.get().getPassword())) {
			return "Invalid Email or Password";
			
		} else {
			User user = isUser.get();
			String jwt = Jwts.builder()
					.claim("id", user.getId())
					.claim("firstName", user.getFirstName())
					.claim("lastName", user.getLastName())
					.setIssuedAt(Date.from(now))
					.setExpiration(Date.from(now.minus(7, ChronoUnit.DAYS)))
					.signWith(Keys.hmacShaKeyFor(secret))
					.compact();

			return jwt;
		}
	}
}
