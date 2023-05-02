package com.onerivet.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onerivet.entity.UserEntity;
import com.onerivet.repository.UserRepository;

@Service
public class UserServiceInfo implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<UserEntity> user = repository.findByusername(username);
	        return user.map(UserDetailService::new)
	                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	    }

}
