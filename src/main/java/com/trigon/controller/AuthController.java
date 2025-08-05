package com.trigon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.authresponse.AuthResponse;
import com.trigon.config.JwtProvider;
import com.trigon.entity.Cart;
import com.trigon.entity.User;
import com.trigon.exception.UserException;
import com.trigon.repository.UserRepository;
import com.trigon.request.LoginRequest;
import com.trigon.service.CartService;
import com.trigon.service.CustomUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository repo;
	@Autowired
	JwtProvider jwtprovider;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	CustomUserServiceImpl userserviceImpl;
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/signup")
	private ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		String password=user.getPassword();
		
		User isMAilExist=repo.findByEmail(email);
		
		if(isMAilExist!=null) {
			throw new UserException("Already accoun exists with this mail");
		}
		
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setPassword( passwordEncoder.encode(password));
		
		User savedUser=repo.save(createdUser);
		
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtprovider.generateToken(authentication);
		
		AuthResponse response=new AuthResponse(token,"signup successfull",email);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	private ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String email=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtprovider.generateToken(authentication);
		AuthResponse response=new AuthResponse(token,"Login success",email);
		
		return ResponseEntity.ok(response);
	}

	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetail=userserviceImpl.loadUserByUsername(email);
		
		if(userDetail==null) {
			throw new BadCredentialsException("Invalid UserName");
		}
		
		if(!passwordEncoder.matches(password, userDetail.getPassword())) {
			throw new BadCredentialsException("Password is not correct");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
	}
}
