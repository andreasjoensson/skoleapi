package com.example.skoleapi.repo;

import com.example.skoleapi.models.College;
import com.example.skoleapi.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UniversityRepo extends JpaRepository<University, Long> {
    @Query(value = "SELECT * FROM universitys WHERE city = ?1", nativeQuery = true)
    public List<University> findUniversityByCity(String city);


    @Transactional
    @Modifying
    @Query(value = "UPDATE universitys c SET c.name = ?1, c.logo = ?2, c.type = ?3, c.city = ?4 WHERE c.id = ?5", nativeQuery = true)
    void updateUniversity(String name, String logo, String type, String city, long collegeID);

}
