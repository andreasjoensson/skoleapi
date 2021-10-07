package com.example.skoleapi.controllers;

import com.example.skoleapi.models.College;
import com.example.skoleapi.repo.CollegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollegeController {

    @Autowired
    CollegeRepo collegeRepo;

    @GetMapping("/getColleges")
    public List<College> getColleges(){
        return collegeRepo.findAll();
    }

    @GetMapping("/getColleges/{city}")
    public List<College> getCollegesFromCity(@PathVariable String city){
        return collegeRepo.findCollegeByCity(city);
    }

    @PostMapping("/createCollege")
    public String createCollege(@RequestBody College newCollege){
        collegeRepo.save(newCollege);
        return "College er tilføjet.";
    }

    @PutMapping("/updateCollege/{id}")
    public String updateCollege(@PathVariable Long id, @RequestBody College newCollege){
        if(collegeRepo.existsById(id)){
            collegeRepo.updateCollege(newCollege.getName(),newCollege.getLogo(),newCollege.getType(),newCollege.getCity(),id);
            return "College er nu opdateret.";
        }
        return "College med ID: "+ id + " eksistere ikke...";
    }

    @PatchMapping("/updateCollege/{id}")
    public String updateCollegePatch(@PathVariable("id") Long id, @RequestBody College newCollege){
        return collegeRepo.findById(id).map(foundCollege -> {
            if (newCollege.getName() != null) foundCollege.setName(newCollege.getName());
            if (newCollege.getLogo() != null) foundCollege.setLogo(newCollege.getLogo());
            if (newCollege.getType() != null) foundCollege.setType(newCollege.getType());
            if (newCollege.getCity() != null) foundCollege.setCity(newCollege.getCity());
            collegeRepo.save(foundCollege);
            return "College opdateret";
        }).orElse("College er ikke fundet");
    }

    @DeleteMapping("/deleteCollege/{id}")
    public String deleteCollege(@PathVariable("id") long id){
        if(collegeRepo.existsById(id)){
            collegeRepo.deleteById(id);
            return "College er nu blevet slettet.";
        }
       return "College eksistere ikke";
    }
}
