package com.example.skoleapi.controllers;
import com.example.skoleapi.models.University;
import com.example.skoleapi.repo.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepo universityRepo;

    @GetMapping("/getUniversities")
    public List<University> getUni(){
        return universityRepo.findAll();
    }

    @GetMapping("/getUniversities/{city}")
    public List<University> getUniFromCity(@PathVariable String city){
        return universityRepo.findUniversityByCity(city);
    }

    @PostMapping("/createUniversity")
    public String createUniversity(@RequestBody University newUniversity){
        universityRepo.save(newUniversity);
        return "University er tilføjet.";
    }

    @PutMapping("/updateUniversity/{id}")
    public String updateUniversity(@PathVariable Long id, @RequestBody University newUniversity){
        if(universityRepo.existsById(id)){
            universityRepo.updateUniversity(newUniversity.getName(),newUniversity.getLogo(),newUniversity.getType(),newUniversity.getCity(),id);
            return "University er nu opdateret.";
        }
        return "University med ID: "+ id + " eksistere ikke...";
    }

    @PatchMapping("/updateUniversity/{id}")
    public String updateUniversityPatch(@PathVariable("id") long id, @RequestBody University newUniversity){
        return universityRepo.findById(id).map(foundCollege -> {
            if (newUniversity.getName() != null) foundCollege.setName(newUniversity.getName());
            if (newUniversity.getLogo() != null) foundCollege.setLogo(newUniversity.getLogo());
            if (newUniversity.getType() != null) foundCollege.setType(newUniversity.getType());
            if (newUniversity.getCity() != null) foundCollege.setCity(newUniversity.getCity());
            universityRepo.save(foundCollege);
            return "University opdateret";
        }).orElse("University er ikke fundet");
    }

    @DeleteMapping("/deleteUniversity/{id}")
    public String deleteUniversity(@PathVariable("id") long id){
        if(universityRepo.existsById(id)){
            universityRepo.deleteById(id);
            return "University er nu blevet slettet.";
        }
        return "University eksistere ikke";
    }
}
