package com.example.achar.repository;

import com.example.achar.model.PhotoTec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoTecRepo extends JpaRepository<PhotoTec , Long> {

}
