package com.example.achar.repository;

import com.example.achar.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<Manager , Long> {
}
