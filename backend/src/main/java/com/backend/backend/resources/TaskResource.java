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

import com.backend.backend.domain.Task;
import com.backend.backend.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		Task Task = service.findById(id);
		return ResponseEntity.ok().body(Task);
	}
	
	@GetMapping(value = "/list-all")
	public ResponseEntity<List<Task>> findAll() {
		List<Task> Tasks = service.findAll();
		return ResponseEntity.ok().body(Tasks);
	}
	
	@GetMapping
	public ResponseEntity<Page<Task>> findPageable(@RequestParam(value = "page", defaultValue = "0") Integer page, 
												   @RequestParam(value = "size", defaultValue = "10") Integer size,
												   @RequestParam(value = "direction", defaultValue = "ASC") String direction,
												   @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Task> Tasks = service.findPageable(page, size, direction, orderBy);
		return ResponseEntity.ok().body(Tasks);
	}

	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task Task) {
		return ResponseEntity.ok().body(service.create(Task));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task Task) {
		return ResponseEntity.ok().body(service.update(id, Task));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
