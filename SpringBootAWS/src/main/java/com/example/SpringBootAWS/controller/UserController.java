package com.example.SpringBootAWS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootAWS.entity.User;
import com.example.SpringBootAWS.exception.ResourceNotFoundException;
import com.example.SpringBootAWS.repository.UserRepository;

@RestController
@RequestMapping("/api/Users")
public class UserController {
		
	@Autowired
	UserRepository userRepository;
	
	// get all the users.
	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("Fetching user");
		return this.userRepository.findAll();		
	}
	
	// get specific user by Id.
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId ) {
		System.out.println("Getting user by Id");
		return this.userRepository.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("Resource not found for Userid" + userId));
	}
	
	// Create User.
	@PostMapping
	public User createUser(@RequestBody User user) {	
		System.out.println("Creating user");
		return this.userRepository.save(user);
		
	}
	
	// update the user 
	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable (value = "id") long userId) {
		System.out.println("Updating user by Id");
		User existingUser =  this.userRepository.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("Resource not found for Userid" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
		
	}
	
	// delete user by Id.
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@RequestBody User user, @PathVariable (value = "id") long userId) {
		System.out.println("Deleting user by Id");
		User existingUser =  this.userRepository.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("Resource not found for Userid" + userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
		
	}

}
