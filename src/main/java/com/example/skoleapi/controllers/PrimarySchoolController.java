package com.example.skoleapi.controllers;
import com.example.skoleapi.models.PrimarySchool;
import com.example.skoleapi.repo.PrimarySchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrimarySchoolController {

    @Autowired
    PrimarySchoolRepo primarySchoolRepo;

    @GetMapping("/getPrimarySchools")
    public List<PrimarySchool> getPrimarySchools(){
        return primarySchoolRepo.findAll();
    }

    @GetMapping("/getPrimarySchools/{city}")
    public List<PrimarySchool> getPrimarySchoolsFromCity(@PathVariable String city){
        return primarySchoolRepo.findPrimarySchoolByCity(city);
    }

    @PostMapping("/createPrimarySchool")
    public String createPrimarySchool(@RequestBody PrimarySchool newPrimarySchool){
        primarySchoolRepo.save(newPrimarySchool);
        return "Primary School er tilføjet.";
    }

    @PutMapping("/updatePrimarySchool/{id}")
    public String updatePrimarySchool(@PathVariable Long id, @RequestBody PrimarySchool primarySchool){
        if(primarySchoolRepo.existsById(id)){
            primarySchoolRepo.updatePrimarySchool(primarySchool.getName(),primarySchool.getLogo(),primarySchool.getType(),primarySchool.getCity(),id);
            return "Primary School er nu opdateret.";
        }
        return "Primary School med ID: "+ id + " eksistere ikke...";
    }

    @PatchMapping("/updatePrimarySchool/{id}")
    public String updatePrimarySchoolPatch(@PathVariable("id") Long id, @RequestBody PrimarySchool primarySchool){
        return primarySchoolRepo.findById(id).map(foundCollege -> {
            if (primarySchool.getName() != null) foundCollege.setName(primarySchool.getName());
            if (primarySchool.getLogo() != null) foundCollege.setLogo(primarySchool.getLogo());
            if (primarySchool.getType() != null) foundCollege.setType(primarySchool.getType());
            if (primarySchool.getCity() != null) foundCollege.setCity(primarySchool.getCity());
            primarySchoolRepo.save(foundCollege);
            return "Primary School opdateret";
        }).orElse("Primary School er ikke fundet");
    }

    @DeleteMapping("/deletePrimarySchool/{id}")
    public String deletePrimarySchool(@PathVariable("id") long id){
        if(primarySchoolRepo.existsById(id)){
            primarySchoolRepo.deleteById(id);
            return "Primary School er nu blevet slettet.";
        }
        return "Primary School eksistere ikke";
    }

}