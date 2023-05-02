package com.onerivet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onerivet.entity.UserEntity;
import com.onerivet.payload.UserPayload;
import com.onerivet.service.JwtService;
import com.onerivet.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private JwtService jwt;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@GetMapping("/msg")
	public String msg() {
		return "Hello World";
	}
	
	@PostMapping("/add")
	public String add(@RequestBody UserEntity user) {
		return service.add(user);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserEntity> viewall(){
		return service.viewall();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public  Optional<UserEntity> getbyid(@PathVariable int id) {
	return service.getbyid(id);
	}
	
	 
	@PostMapping("/auth")
	public String authenticateAndGetToken(@RequestBody UserPayload authRequest){
		Authentication authentication=authmanager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwt.generateToken(authRequest.getUsername());	
		}else {
		throw new UsernameNotFoundException("Invalid User!");
	}
	}
}
