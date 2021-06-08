package com.backend.backend.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.backend.domain.Task;
import com.backend.backend.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repo;
	
	public Task findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Task"));
	}
	
	public List<Task> findAll() {
		return repo.findAll();
	}
	
	public Page<Task> findPageable(Integer page, 
								   Integer size,
								   String direction,
								   String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.fromString(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	@Transactional
	public Task create(Task Task) {
		Task.setId(null);
		return repo.save(Task);
	}
	
	@Transactional
	public Task update(Long id, Task newObj) {
		Task obj = findById(id);
		updateObjData(newObj, obj);
		return repo.save(obj);
	}
	
	@Transactional
	public void delete(Long id) {
		repo.findById(id);
		repo.deleteById(id);
	}

	private void updateObjData(Task newObj, Task obj) {
		obj.setUser(newObj.getUser());
		obj.setDescription(newObj.getDescription());
		obj.setPriority(newObj.getPriority());
		obj.setStatus(newObj.getStatus());
	}
}
