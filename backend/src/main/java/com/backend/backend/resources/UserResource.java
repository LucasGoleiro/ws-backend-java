package com.backend.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.domain.User;
import com.backend.backend.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "/list-all")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> findPageable(@RequestParam(value = "page", defaultValue = "0") Integer page, 
												   @RequestParam(value = "size", defaultValue = "10") Integer size,
												   @RequestParam(value = "direction", defaultValue = "ASC") String direction,
												   @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<User> users = service.findPageable(page, size, direction, orderBy);
		return ResponseEntity.ok().body(users);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return ResponseEntity.ok().body(service.create(user));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.ok().body(service.update(id, user));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
