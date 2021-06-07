package com.backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
