package com.example.skoleapi.repo;
import com.example.skoleapi.models.PrimarySchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface PrimarySchoolRepo extends JpaRepository<PrimarySchool, Long> {
    @Query(value = "SELECT * FROM primaryschools WHERE city = ?1", nativeQuery = true)
    List<PrimarySchool> findPrimarySchoolByCity(String city);

    @Transactional
    @Modifying
    @Query(value = "UPDATE primaryschools c SET c.name = ?1, c.logo = ?2, c.type = ?3, c.city = ?4 WHERE c.id = ?5", nativeQuery = true)
    void updatePrimarySchool(String name, String logo, String type, String city, long collegeID);

}
