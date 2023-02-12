package com.example.achar.repository;

import com.example.achar.model.services.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Services , Long> {

    Services readServicesByName(String name);
}
