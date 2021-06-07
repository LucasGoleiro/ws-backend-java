package com.backend.backend.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.backend.domain.User;
import com.backend.backend.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "User"));
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public Page<User> findPageable(Integer page, 
								   Integer size,
								   String direction,
								   String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.fromString(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	@Transactional
	public User create(User user) {
		user.setId(null);
		return repo.save(user);
	}
	
	@Transactional
	public User update(Long id, User newObj) {
		User obj = findById(id);
		updateObjData(newObj, obj);
		return repo.save(obj);
	}
	
	@Transactional
	public void delete(Long id) {
		repo.findById(id);
		repo.deleteById(id);
	}

	private void updateObjData(User newObj, User obj) {
		obj.setName(newObj.getName());
		obj.setEmail(newObj.getEmail());
		obj.setPassword(newObj.getPassword());
		obj.setPosition(newObj.getPosition());
	}

}
