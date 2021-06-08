package com.backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.domain.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long>{

}
