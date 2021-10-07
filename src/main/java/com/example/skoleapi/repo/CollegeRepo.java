package com.example.skoleapi.repo;

import com.example.skoleapi.models.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CollegeRepo extends JpaRepository<College, Long> {
    @Query(value = "SELECT * FROM colleges WHERE city = ?1", nativeQuery = true)
    public List<College> findCollegeByCity(String city);

    @Transactional
    @Modifying
    @Query(value = "UPDATE colleges c SET c.name = ?1, c.logo = ?2, c.type = ?3, c.city = ?4 WHERE c.id = ?5", nativeQuery = true)
    void updateCollege(String name, String logo, String type, String city, long collegeID);
}
