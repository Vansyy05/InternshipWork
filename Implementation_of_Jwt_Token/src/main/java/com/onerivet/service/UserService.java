package com.onerivet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.onerivet.entity.UserEntity;
import com.onerivet.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public String add(UserEntity user) {
    user.setPassword(encoder.encode(user.getPassword()));
	 repository.save(user);
	 return "Data Inserted Successfully...";
	}
	
	public List<UserEntity> viewall(){
	return repository.findAll();
	}
	
	public Optional<UserEntity> getbyid(int id) {
		return repository.findById(id);
	}
	
}
