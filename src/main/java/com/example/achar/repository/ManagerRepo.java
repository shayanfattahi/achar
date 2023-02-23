package com.example.achar.repository;

import com.example.achar.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface ManagerRepo extends JpaRepository<Manager , Long >{

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM technician_under_services WHERE technician_id =?1 and under_services_id =?2" , nativeQuery = true)
    void deleteUnderserviceAndTech(Long id_tech , Long id_under);


}
