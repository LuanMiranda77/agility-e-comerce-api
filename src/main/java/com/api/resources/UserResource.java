package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Usuario;
import com.api.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserResource implements ResourceBase<Usuario, Long>{
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody Usuario pEntity, HttpServletResponse response){
		Usuario userSalvo = userRepository.findByEmail(pEntity.getEmail());
		
		System.out.println(userSalvo.getPassword());
		if(userSalvo != null) {
			if(userSalvo.getEmail().equals(pEntity.getEmail()) 
					&& userSalvo.getPassword().equals(pEntity.getPassword())) {
				
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
				
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		
	}

	@Override
	public ResponseEntity<Usuario> save(@Valid Usuario pEntity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Usuario> update(@Valid Long pID, Usuario pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long pID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseEntity<Usuario> findById(Long pID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> findAllPage(Usuario pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAllList() {
		// TODO Auto-generated method stub
		return null;
	}

}
