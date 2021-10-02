package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.api.domain.Usuario;
import com.api.repository.UserRepository;
import com.api.services.exceptions.UserNameNotFoundException;

@Repository
public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByEmail(username);
		
		if (user == null) {
			 new UserNameNotFoundException("Usuario não encotrado!");
		}
			
		return (UserDetails) user;
	}

}
